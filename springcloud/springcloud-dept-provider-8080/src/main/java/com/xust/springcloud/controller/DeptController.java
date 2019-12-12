package com.xust.springcloud.controller;

import com.xust.springcloud.entities.Dept;
import com.xust.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/delete/{id}")
    boolean delete(@PathVariable("id") Long id){
        return  deptService.delete(id);
    }

    @RequestMapping(value = "/dept/update",method = RequestMethod.POST)
    boolean update(@RequestBody Dept dept){
        return  deptService.update(dept);
    }

    @RequestMapping(value = "/dept/get/{id}")
    Dept get(@PathVariable("id") Long id){
        return deptService.get(id);
    }

    @RequestMapping(value = "/dept/list")
    List<Dept> list(){
        return deptService.list();
    }





}
