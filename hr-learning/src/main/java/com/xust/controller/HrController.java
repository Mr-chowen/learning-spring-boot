package com.xust.controller;

import com.xust.common.util.CommonResult;
import com.xust.model.Hr;
import com.xust.model.Role;
import com.xust.service.HrService;
import com.xust.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "HrController",description = "Hr管理")
@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;

    @Autowired
    RoleService roleService;

    @ApiOperation("获取所有hr信息")
    @GetMapping("/")
    @ResponseBody
    public List<Hr> getAllHrs(String keywords){
        return hrService.getAllHrs(keywords);
    }

    @ApiOperation("获取所有角色信息")
    @GetMapping("/roles")
    @ResponseBody
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }


    @ApiOperation("更新hr信息")
    @PutMapping("/")
    @ResponseBody
    public CommonResult updateHr(@RequestBody Hr hr){
        if(hrService.updateHr(hr)==1){
            return CommonResult.ok("更新成功！");
        }
        return CommonResult.error("更新失败！");
    }

    @ApiOperation("更新角色信息")
    @PutMapping("/roles")
    @ResponseBody
    public CommonResult updateHrRole(Integer hrid,Integer[] rids){
        if(hrService.updateHrRole(hrid,rids)){
            return CommonResult.ok("更新成功！");
        }
        return CommonResult.error("更新失败！");
    }

    @ApiOperation("根据id删除hr信息")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult deleteHrById(@PathVariable Integer id){
        if(hrService.deleteHrById(id)==1){
            return CommonResult.ok("删除成功！");
        }
        return CommonResult.error("删除失败！");
    }

}
