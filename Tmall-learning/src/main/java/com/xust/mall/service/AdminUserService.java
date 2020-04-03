package com.xust.mall.service;

import com.xust.mall.model.AdminUser;

public interface AdminUserService {

    AdminUser login(String userName,String passWord);
    /**
     * 获取用户信息
     * @param loginUserId
     * @return
     */
    AdminUser findUserById(Integer loginUserId);

    /**
     * 修改当前登录用户密码
     * @param loginUserId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId,String oldPassword,String newPassword);

    /**
     * 修改当前登录用户的名称
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Integer loginUserId,String loginUserName,String nickName);
}
