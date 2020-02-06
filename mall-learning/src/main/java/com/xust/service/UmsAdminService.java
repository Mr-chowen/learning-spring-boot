package com.xust.service;

import com.xust.mbg.model.UmsAdmin;
import com.xust.mbg.model.UmsPermission;

import java.util.List;

public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取用户所有权限
     * @param id
     * @return
     */
    List<UmsPermission> getPermissionList(Long id);

    /**
     * 注册功能
     * @param umsAdminParam
     * @return
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return
     */
    String login(String username, String password);
}
