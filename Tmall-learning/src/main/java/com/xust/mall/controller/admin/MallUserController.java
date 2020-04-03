package com.xust.mall.controller.admin;

import com.xust.mall.common.CommonResult;
import com.xust.mall.common.ResultCode;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class MallUserController {
    @Autowired
    private MallUserService mallUserService;

    @GetMapping("/users")
    public String usersPage(HttpServletRequest request){
        request.setAttribute("path","users");
        return "admin/mall_user";
    }

    @GetMapping("/users/list")
    @ResponseBody
    public CommonResult list(@RequestParam Map<String,Object> params){
        if(StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultCode.FailResult("请求参数异常");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultCode.SuccessResult(mallUserService.getMallUserPage(pageUtil));
    }

    /**
     * 用户状态修改(0-未锁定，1-已锁定)
     * @param ids
     * @param lockStatus
     * @return
     */
    @PostMapping("/users/lock/{lockStatus}")
    @ResponseBody
    public CommonResult delete(@RequestBody Integer[] ids,@PathVariable int lockStatus){
        if(ids.length < 1){
            return ResultCode.FailResult("请求参数异常");
        }
        if(lockStatus != 0 && lockStatus != 1){
            return ResultCode.FailResult("操作异常");
        }
        if(mallUserService.lockUser(ids,lockStatus)){
            return ResultCode.SuccessResult();
        } else {
            return ResultCode.FailResult("禁用失败");
        }

    }

}
