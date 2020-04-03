package com.xust.mall.controller.mall;

import com.xust.mall.common.CommonResult;
import com.xust.mall.common.ResultCode;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.ConstantsUtil;
import com.xust.mall.common.utils.MD5Util;
import com.xust.mall.model.MallUser;
import com.xust.mall.service.MallUserService;
import com.xust.mall.vo.MallUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PersonalController {
    @Autowired
    private MallUserService mallUserService;

    @GetMapping("/personal")
    public String personalPage(HttpServletRequest request, HttpSession session){
        request.setAttribute("path","personal");
        return "mall/personal";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        return "mall/login";
    }

    /**
     * 登录页
     * @return
     */
    @GetMapping({"/login","login.html"})
    public String loginPage(){
        return "mall/login";
    }

    /**
     * 注册页
     * @return
     */
    @GetMapping({"/register","register.html"})
    public String registerPage(){
        return "mall/register";
    }

    @GetMapping("/personal/addresses")
    public String addressPage(){
        return "mall/addresses";
    }

    /**
     * 登录处理
     * @param loginName
     * @param verifyCode
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@RequestParam("loginName") String loginName,
                              @RequestParam("verifyCode") String verifyCode,
                              @RequestParam("password") String password,
                              HttpSession session){

        if(StringUtils.isEmpty(loginName)){
            return ResultCode.FailResult(ServiceResultEnum.LOGIN_NAME_NULL.getMessage());
        }
        if(StringUtils.isEmpty(verifyCode)){
            return ResultCode.FailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_NULL.getMessage());
        }
        if(StringUtils.isEmpty(password)){
            return ResultCode.FailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getMessage());
        }

        String  kaptchaCode = session.getAttribute(ConstantsUtil.MALL_VERIFY_CODE_KEY) + "";

        if(StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)){
            return ResultCode.FailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_ERROR.getMessage());
        }

        String loginResult = mallUserService.login(loginName, MD5Util.MD5Encode(password,"UTF-8"),session);
        //登录成功
        if(ServiceResultEnum.SUCCESS.getMessage().equals(loginResult)){
            return ResultCode.SuccessResult();
        }
        //登录失败
        return ResultCode.FailResult(loginResult);
    }

    /**
     * 注册处理
     * @param loginName
     * @param password
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public CommonResult register(@RequestParam("loginName") String loginName,
                                 @RequestParam("password") String password){
        String registerResult = mallUserService.register(loginName,password);

        if(ServiceResultEnum.SUCCESS.getMessage().equals(registerResult)){
            return ResultCode.SuccessResult();
        }
        return ResultCode.FailResult(registerResult);
    }

    /**
     * 修改信息
     * @param mallUser
     * @param session
     * @return
     */
    @PostMapping("/personal/updateInfo")
    @ResponseBody
    public CommonResult updateInfo(@RequestBody MallUser mallUser, HttpSession session){
        MallUserVO mallUserVO = mallUserService.updateUserInfo(mallUser,session);
        if(mallUserVO == null){
            return ResultCode.FailResult("修改失败");
        } else {

            return ResultCode.SuccessResult();
        }
    }

}
