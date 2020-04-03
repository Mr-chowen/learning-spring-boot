package com.xust.mall.controller.admin;


import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.model.AdminUser;
import com.xust.mall.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    /**
     * 登录页
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    /**
     * 主页
     * @param request
     * @return
     */
    @GetMapping({"","/","/index","/index.html"})
    public String index(HttpServletRequest request){
        request.setAttribute("path","index");
        request.setAttribute("categoryCount",0);
        request.setAttribute("blogCount",0);
        request.setAttribute("linkCount",0);
        request.setAttribute("tagCount",0);
        request.setAttribute("commentCount",0);
        return "admin/index";
    }

    /**
     * 处理登录请求
     * @param userName
     * @param passWord
     * @param verifyCode
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord, @RequestParam("verifyCode") String verifyCode, HttpSession session){
        if(StringUtils.isEmpty(verifyCode)){
            session.setAttribute("error","验证码不能为空");
            return "admin/login";
        }

        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)){
            session.setAttribute("error","用户名或密码不能为空");
            return "admin/login";
        }

        String kaptcha = session.getAttribute("verifyCode")+"";
        if(StringUtils.isEmpty(kaptcha) || !verifyCode.equals(kaptcha)){
            session.setAttribute("error","验证码错误");
            return "admin/login";
        }

        AdminUser adminUser = adminUserService.login(userName,passWord);
        if(adminUser != null){
            session.setAttribute("loginUser",adminUser.getNickName());
            session.setAttribute("loginUserId",adminUser.getAdminUserId());
            //session过期时间设置为6小时
            session.setMaxInactiveInterval(60*60*6);
            return "redirect:/admin/index";
        }else{
            session.setAttribute("error","登录失败，请联系管理员");
            return "admin/login";
        }
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request){
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.findUserById(loginUserId);
        if(adminUser == null){
            return "admin/login";
        }
        request.setAttribute("path","profile");
        request.setAttribute("loginUserName",adminUser.getLoginUserName());
        request.setAttribute("nickName",adminUser.getNickName());
        return "admin/profile";
    }

    /**
     * 修改当前登录用户的密码
     * @param request
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/profile/password")
    @ResponseBody
    public String updatePassword(HttpServletRequest request,@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword){
        if(StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)){
            return ServiceResultEnum.LOGIN_PASSWORD_NULL.getMessage();
        }

        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        if(adminUserService.updatePassword(loginUserId,oldPassword,newPassword)){
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("error");
            return ServiceResultEnum.SUCCESS.getMessage();
        }else{
            return ServiceResultEnum.ERROR.getMessage();
        }
    }

    /**
     * 修改当前登录的用户名
     * @param request
     * @param loginUserName
     * @param nickName
     * @return
     */
    @PostMapping("/profile/name")
    @ResponseBody
    public String updateName(HttpServletRequest request,@RequestParam("loginUserName") String loginUserName,@RequestParam("nickName") String nickName){
        if(StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)){
            return ServiceResultEnum.FAILED.getMessage();
        }

        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        if(adminUserService.updateName(loginUserId,loginUserName,nickName)){
            return ServiceResultEnum.SUCCESS.getMessage();
        }else{
            return ServiceResultEnum.FAILED.getMessage();
        }
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("error");
        return "admin/login";
    }
}
