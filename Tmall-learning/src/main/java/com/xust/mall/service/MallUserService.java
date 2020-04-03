package com.xust.mall.service;

import com.xust.mall.common.CommonPage;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.MallUser;
import com.xust.mall.vo.MallUserVO;

import javax.servlet.http.HttpSession;

public interface MallUserService {
    CommonPage getMallUserPage(PageUtil pageUtil);

    String register(String loginName,String password);

    String login(String loginName, String passwordMD5, HttpSession session);

    MallUserVO updateUserInfo(MallUser mallUser, HttpSession session);

    Boolean lockUser(Integer[] ids,int lockStatus);
}

