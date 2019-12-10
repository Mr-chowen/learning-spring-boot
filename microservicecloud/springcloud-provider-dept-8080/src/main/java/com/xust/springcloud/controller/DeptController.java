package com.xust.springcloud.controller;

import com.xust.springcloud.entities.Dept;
import com.xust.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    DeptService deptService;

    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return  deptService.addDept(dept);
    }
    @RequestMapping(value = "/dept/delete/{id}",method = RequestMethod.GET)
    public Boolean delete(@PathVariable("id") Long id){
        return  deptService.deleteDept(id);
    }
    @RequestMapping(value = "/dept/update",method = RequestMethod.GET)
    public boolean update(Dept dept){
        return  deptService.updateDept(dept);
    }
    @RequestMapping(value = "/dept/get/{id}",method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id){
        return  deptService.get(id);
    }

    @RequestMapping(value = "/dept/list",method = RequestMethod.GET)
    public List<Dept> list(){
        return  deptService.list();
    }
}
