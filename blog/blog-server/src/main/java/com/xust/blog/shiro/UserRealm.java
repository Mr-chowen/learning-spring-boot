package com.xust.blog.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.xust.blog.entity.User;
import com.xust.blog.service.UserService;
import com.xust.blog.utils.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;

        String userId = jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        User user = userService.getById(Long.valueOf(userId));

        if(user == null){
            throw new UnknownAccountException("用户不存在，请注册");
        }

        if(user.getStatus() == -1){
            throw new LockedAccountException("用户被锁定，请联系管理员");
        }

        UserProfile profile = new UserProfile();

        BeanUtil.copyProperties(user,profile);

        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}
