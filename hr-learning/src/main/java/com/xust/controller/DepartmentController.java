package com.xust.controller;

import com.xust.common.util.CommonResult;
import com.xust.model.Department;
import com.xust.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "DepartmentController",description = "部门管理")
@RestController
@RequestMapping("/system/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @ApiOperation("查询所有部门信息")
    @GetMapping("/")
    @ResponseBody
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation("添加部门")
    @PostMapping("/")
    @ResponseBody
    public CommonResult addDepartment(@RequestBody Department department){
        departmentService.addDepartment(department);
        if(department.getResult() == 1){
            System.out.println(department.getResult());
            return CommonResult.ok("添加成功！",department);
        }
        return CommonResult.error("添加失败！");
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult deleteDepartmentById(@PathVariable Integer id){
        Department department = new Department();
        department.setId(id);
        departmentService.deleteDepartmentById(department);
        if(department.getResult() == -2){
            return CommonResult.error("该部门下有子部门，删除失败！");
        }else if(department.getResult() == -1){
            return CommonResult.error("该部门下有员工，删除失败！");
        }else if(department.getResult() == 1){
            return CommonResult.ok("删除成功！");
        }
        return CommonResult.error("删除失败！");

    }

    @ApiOperation("修改部门信息")
    @PutMapping("/")
    @ResponseBody
    public CommonResult updateDepartmentById(@RequestBody Department department){
        if(departmentService.updateDepartment(department) == 1){
            return CommonResult.ok("更新成功！");
        }
        return CommonResult.error("更新失败！");
    }

}
