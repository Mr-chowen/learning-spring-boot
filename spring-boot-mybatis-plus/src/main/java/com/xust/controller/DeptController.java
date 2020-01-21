package com.xust.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xust.entity.Dept;
import com.xust.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @Autowired
    private DeptMapper deptMapper;

    @RequestMapping("/get/{id}")
    public Dept get(@PathVariable("id") long id){
        return deptMapper.selectById(id);
    }

    @RequestMapping("/page")
    public IPage<Dept> selectWithPage(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        QueryWrapper<Dept> queryWrapper=new QueryWrapper<>();
        IPage<Dept> iPage=new Page<>(pageNo,pageSize);
        return deptMapper.selectPage(iPage,queryWrapper);
    }

}
