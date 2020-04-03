package com.xust.mall.service.impl;

import com.xust.mall.common.utils.MD5Util;
import com.xust.mall.mapper.AdminUserMapper;
import com.xust.mall.model.AdminUser;
import com.xust.mall.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String passWord) {
       String passwordMD5 = MD5Util.MD5Encode(passWord,"UTF-8");
        return adminUserMapper.login(userName,passwordMD5);
    }

    @Override
    public AdminUser findUserById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String oldPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户是否存在
        if(adminUser != null){
            String oldPasswordMD5 = MD5Util.MD5Encode(oldPassword,"UTF-8");
            String newPasswordMD5 = MD5Util.MD5Encode(newPassword,"UTF-8");
            //原密码输入是否正确
            if(oldPasswordMD5.equals(adminUser.getLoginPassWord())){
                //设置新密码
                adminUser.setLoginPassWord(newPasswordMD5);
                if(adminUserMapper.updateByPrimaryKeySelective(adminUser)>0){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        if(adminUser != null){
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if(adminUserMapper.updateByPrimaryKeySelective(adminUser)>0){
                return true;
            }
        }
        return false;
    }
}
