package com.xust.controller;

import com.xust.common.util.CommonResult;
import com.xust.common.util.POIUtils;
import com.xust.common.util.PageBean;
import com.xust.model.*;
import com.xust.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Api(tags = "EmployeeController",description = "员工管理")
@RestController
@RequestMapping("/system/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    NationService nationService;

    @Autowired
    PoliticsstatusService politicsstatusService;

    @Autowired
    JobLevelService jobLevelService;

    @Autowired
    PositionService positionService;

    @Autowired
    DepartmentService departmentService;

    @ApiOperation("分页查询员工信息")
    @GetMapping("/")
    public PageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, Date[] beginDateScope){
        return employeeService.getEmployeeByPage(page,size,employee,beginDateScope);
    }

    @ApiOperation("添加员工")
    @PostMapping("/")
    @ResponseBody
    public CommonResult addEmployee(@RequestBody Employee employee){
        if(employeeService.addEmployee(employee) == 1){
            return CommonResult.ok("添加成功！");
        }
        return CommonResult.error("添加失败！");
    }


    @ApiOperation("删除员工")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult deleteEmployeeByEid(@PathVariable Integer id){
        if(employeeService.deleteEmployeeByEid(id) == 1){
            return CommonResult.ok("删除成功！");
        }
        return  CommonResult.error("删除失败！");
    }

    @ApiOperation("更新员工")
    @PutMapping("/")
    @ResponseBody
    public CommonResult updateEmployee(@RequestBody Employee employee){
        if(employeeService.updateEmployee(employee) == 1){
            return CommonResult.ok("更新成功！");
        }
        return CommonResult.error("更新失败！");
    }

    @ApiOperation("获取民族信息")
    @GetMapping("/nations")
    @ResponseBody
    public List<Nation> getAllNations(){
        return nationService.getAllNations();
    }

    @ApiOperation("获取政治面貌信息")
    @GetMapping("/politicsstatus")
    @ResponseBody
    public List<Politicsstatus> getAllPoliticsstatus(){
        return politicsstatusService.getAllPoliticsstatus();
    }

    @ApiOperation("获取职级信息")
    @GetMapping("/jobLevel")
    @ResponseBody
    public List<JobLevel> getAllJoblevel(){
        return jobLevelService.getAllJobLevels();
    }

    @ApiOperation("获取职位信息")
    @GetMapping("/position")
    @ResponseBody
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @ApiOperation("获取maxWorkID")
    @GetMapping("/maxWorkID")
    @ResponseBody
    public CommonResult maxWorkID(){
        CommonResult result = CommonResult.build().setStatus(200).setObj(String.format("%08d",employeeService.maxWorkID()+1));
        return result;
    }

    @ApiOperation("获取部门信息")
    @GetMapping("/departments")
    @ResponseBody
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation("导出数据")
    @GetMapping("/export")
    @ResponseBody
    public ResponseEntity<byte[]> exportData(){
        List<Employee> list = (List<Employee>)employeeService.getEmployeeByPage(null,null,null,null).getData();
        return POIUtils.employee2Excel(list);
    }

    @ApiOperation("上传数据")
    @PostMapping("/import")
    @ResponseBody
    public CommonResult importData(MultipartFile file) throws IOException{
        List<Employee> list = POIUtils.excel2Employee(file,nationService.getAllNations(),politicsstatusService.getAllPoliticsstatus(),departmentService.getAllDepartments(),positionService.getAllPositions(),jobLevelService.getAllJobLevels());
        if(employeeService.addEmployees(list) == list.size()){
            return CommonResult.ok("上传成功！");
        }
        return CommonResult.error("上传失败！");
    }

}
