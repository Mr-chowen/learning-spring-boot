package com.xust.cache.controller;

import com.xust.cache.bean.Employee;
import com.xust.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

   @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return  employee;
    }

    @GetMapping("/emp")
    public Employee updateEmployee(Employee employee){
        Employee emp = employeeService.updateEmployee(employee);
        return emp;
    }

    @GetMapping("/emp/delete/{id}")
    public Integer deleteEmployee(@PathVariable("id") Integer id){
       employeeService.deleteEmp(id);
       return id;
    }

    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
        return employeeService.getEmpByLastName(lastName);
    }
}
