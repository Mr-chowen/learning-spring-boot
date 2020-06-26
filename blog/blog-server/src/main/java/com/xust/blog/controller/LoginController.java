package com.xust.blog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xust.blog.common.CommonResult;
import com.xust.blog.entity.User;
import com.xust.blog.service.UserService;
import com.xust.blog.utils.JwtUtil;
import com.xust.blog.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "LoginController",description = "登录管理")
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult login(@Validated @RequestBody LoginVO loginVO, HttpServletResponse response){

        User user = userService.getOne(new QueryWrapper<User>().eq("username",loginVO.getUsername()));

        Assert.notNull(user,"该用户不存在");

        if(!user.getPassword().equals(SecureUtil.md5(loginVO.getPassword()))){
            return CommonResult.fail("密码错误！");
        }

        String jwt = jwtUtil.generateToken(user.getId());

        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose","Authorization");

        return CommonResult.success(MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUsername())
                .put("email",user.getEmail())
                .put("avatar",user.getAvatar())
                .map()
        );
    }

    @ApiOperation("退出")
    @RequiresAuthentication
    @GetMapping("/logout")
    public CommonResult logout(){
        SecurityUtils.getSubject().logout();
        return CommonResult.success(null);
    }

}
