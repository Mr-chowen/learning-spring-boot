package com.xust.mall.service.impl;

import com.xust.mall.common.CommonPage;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.BeanUtil;
import com.xust.mall.common.utils.ConstantsUtil;
import com.xust.mall.common.utils.MD5Util;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.mapper.MallUserMapper;
import com.xust.mall.model.MallUser;
import com.xust.mall.service.MallUserService;
import com.xust.mall.vo.MallUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MallUserServiceImpl implements MallUserService {
    @Autowired
    private MallUserMapper mallUserMapper;

    /**
     * 分页
     * @param pageUtil
     * @return
     */
    @Override
    public CommonPage getMallUserPage(PageUtil pageUtil) {
        List<MallUser> mallUsers = mallUserMapper.selectMallUsers(pageUtil);
        int total = mallUserMapper.getTotalMallUsers(pageUtil);
        CommonPage page = new CommonPage(mallUsers,total,pageUtil.getLimit(),pageUtil.getPage());
        return page;
    }

    /**
     * 注册
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public String register(String loginName, String password) {
        if(mallUserMapper.selectByLoginName(loginName) != null){
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getMessage();
        }
        MallUser user = new MallUser();
        user.setLoginName(loginName);
        user.setNickName(loginName);
        String passwordMD5 = MD5Util.MD5Encode(password,"UTF-8");
        user.setPasswordMd5(passwordMD5);
        if(mallUserMapper.insertSelective(user) > 0){
            return ServiceResultEnum.SUCCESS.getMessage();
        }
        return ServiceResultEnum.DB_ERROR.getMessage();
    }

    /**
     * 登录
     * @param loginName
     * @param passwordMD5
     * @param session
     * @return
     */
    @Override
    public String login(String loginName, String passwordMD5, HttpSession session) {
        MallUser user = mallUserMapper.selectByLoginNameAndPassword(loginName,passwordMD5);
        if(user != null && session != null){
            if(user.getLockedFlag() == 1){
                return ServiceResultEnum.LOGIN_USER_LOCKED.getMessage();
            }
            //昵称处理
            if(user.getNickName() != null && user.getNickName().length() > 10){
                String nickName = user.getNickName().substring(0,10)+"...";
                user.setNickName(nickName);
            }
            MallUserVO mallUserVO = new MallUserVO();
            BeanUtil.copyProperties(user,mallUserVO);
            session.setAttribute(ConstantsUtil.MALL_USER_SESSION_KEY,mallUserVO);
            return ServiceResultEnum.SUCCESS.getMessage();
        }
        return ServiceResultEnum.LOGIN_ERROR.getMessage();
    }

    /**
     * 更新
     * @param mallUser
     * @param session
     * @return
     */
    @Override
    public MallUserVO updateUserInfo(MallUser mallUser, HttpSession session) {
        MallUser user = mallUserMapper.selectByPrimaryKey(mallUser.getUserId());
        if(user != null){
            user.setNickName(mallUser.getNickName());
            user.setAddress(mallUser.getAddress());
            user.setIntroduceSign(mallUser.getIntroduceSign());
            if(mallUserMapper.updateByPrimaryKeySelective(user) > 0){
                MallUserVO mallUserVO = new MallUserVO();
                user = mallUserMapper.selectByPrimaryKey(mallUser.getUserId());
                BeanUtil.copyProperties(user,mallUserVO);
                session.setAttribute(ConstantsUtil.MALL_USER_SESSION_KEY,mallUserVO);
                return mallUserVO;
            }
        }
        return null;
    }

    /**
     * 更新用户状态
     * @param ids
     * @param lockStatus
     * @return
     */
    @Override
    public Boolean lockUser(Integer[] ids, int lockStatus) {
        if(ids.length < 1){
            return false;
        }
        return mallUserMapper.lockUserBatch(ids,lockStatus) > 0;
    }
}
