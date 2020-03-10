package com.xust.controller;

import com.xust.common.util.CommonResult;
import com.xust.common.util.PageBean;
import com.xust.model.Salary;
import com.xust.service.EmployeeService;
import com.xust.service.SalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "SalaryController",description = "薪资管理")
@RestController
@RequestMapping("/system/salary")
public class SalaryController {
    @Autowired
    SalaryService salaryService;

    @Autowired
    EmployeeService employeeService;

    @ApiOperation("获取薪资帐套信息")
    @GetMapping("/")
    @ResponseBody
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }

    @ApiOperation("添加薪资帐套信息")
    @PostMapping("/")
    @ResponseBody
    public CommonResult addSalary(@RequestBody Salary salary){
        if(salaryService.addSalary(salary) == 1){
            return CommonResult.ok("添加成功！");
        }
        return CommonResult.error("添加失败！");
    }

    @ApiOperation("删除薪资帐套信息")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult deleteSalaryById(@PathVariable Integer id){
        if(salaryService.deleteSalaryById(id) == 1){
            return CommonResult.ok("删除成功！");
        }
        return CommonResult.error("删除失败！");
    }

    @ApiOperation("更新薪资帐套信息")
    @PutMapping("/")
    @ResponseBody
    public CommonResult updateSalaryById(@RequestBody Salary salary){
        if(salaryService.updateSalaryById(salary) == 1){
            return CommonResult.ok("更新成功！");
        }
        return CommonResult.error("更新失败！");
    }

    @ApiOperation("获取员工薪资信息")
    @GetMapping("/salary")
    @ResponseBody
    public PageBean getEmployeeByPageWithSalary(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "1") Integer size){
        return employeeService.getEmployeeByPageWithSalary(page,size);
    }

    @ApiOperation("修改员工薪资信息")
    @PutMapping("/salary")
    @ResponseBody
    public CommonResult updateEmployeeSalaryById(Integer eid,Integer sid){
        Integer result = employeeService.updateEmployeeSalaryById(eid,sid);
        if(result == 1 || result == 2){
            return CommonResult.ok("更新成功！");
        }
        return CommonResult.error("更新失败！");
    }

}
