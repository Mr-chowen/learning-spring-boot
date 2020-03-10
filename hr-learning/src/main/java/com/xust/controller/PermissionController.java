package com.xust.controller;

import com.xust.common.util.CommonResult;
import com.xust.model.Menu;
import com.xust.model.Role;
import com.xust.service.MenuService;
import com.xust.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "PermissionController",description = "角色权限管理")
@RestController
@RequestMapping("/system/permission")
public class PermissionController {
    @Autowired
RoleService roleService;

    @Autowired
    MenuService menuService;

    @ApiOperation("获取所有角色")
    @GetMapping("/")
    @ResponseBody
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @ApiOperation("获取所有菜单信息")
    @GetMapping("/menus")
    @ResponseBody
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @ApiOperation("根据rid获取角色信息")
    @GetMapping("/mids/{rid}")
    @ResponseBody
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }

    @ApiOperation("更新菜单角色")
    @PutMapping("/")
    @ResponseBody
    public CommonResult updateMenuRole(Integer rid,Integer[] mids){
        if(menuService.updateMenuRole(rid,mids)){
            return CommonResult.ok("更新成功");
        }
        return CommonResult.error("更新失败！");
    }

    @ApiOperation("添加角色")
    @PostMapping("/")
    @ResponseBody
    public CommonResult addRole(@RequestBody Role role){
        if(roleService.addRole(role) == 1){
            return CommonResult.ok("添加成功！");
        }
        return CommonResult.error("添加失败！");
    }

    @ApiOperation("根据id删除角色信息")
    @DeleteMapping("/role/{rid}")
    @ResponseBody
    public CommonResult deleteRoleById(@PathVariable Integer rid){
        if (roleService.deleteRoleById(rid) == 1){
            return CommonResult.ok("删除成功！");
        }
        return CommonResult.error("删除失败！");
    }

    @ApiOperation("根据hrid获取菜单信息")
    @GetMapping("/menu")
    @ResponseBody
    public List<Menu> getMenusByHrId(){
        return menuService.getMenusByHrId();
    }
}
