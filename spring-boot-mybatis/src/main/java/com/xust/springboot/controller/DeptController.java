package com.xust.springboot.controller;

import com.xust.springboot.bean.Department;
import com.xust.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id){
       return departmentMapper.getDeptById(id);
    }
    @GetMapping("/delete/{id}")
    public int deleteDept(@PathVariable("id") Integer id){
        return departmentMapper.deleteDeptById(id);
    }
    @GetMapping("/dept")
    public Department insertDept(Department department){
        departmentMapper.insertDept(department);
        return  department;
    }
    @GetMapping("/update/{id}")
    public Department updateDept(@PathVariable("id") Integer id,Department department){
        departmentMapper.updateDept(department);
        return  department;
    }
}
