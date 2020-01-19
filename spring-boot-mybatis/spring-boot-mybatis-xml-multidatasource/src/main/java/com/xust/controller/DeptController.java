package com.xust.controller;

import com.xust.entity.Dept;
import com.xust.mapper.dept.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptMapper deptMapper;

    @RequestMapping("/dept/list")
    public List<Dept> list(){
        List<Dept> depts = deptMapper.findAll();
        return depts;
    }

    @RequestMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") long id){
        Dept dept = deptMapper.findById(id);
        return dept;
    }

    @RequestMapping("/dept/add")
    public void add(Dept dept){
        deptMapper.insert(dept);
    }

    @RequestMapping("/dept/edit")
    public void edit(Dept dept){
        deptMapper.update(dept);
    }

    @RequestMapping("/dept/remove/{id}")
    public void remove(@PathVariable("id") long id){
        deptMapper.delete(id);
    }

}
