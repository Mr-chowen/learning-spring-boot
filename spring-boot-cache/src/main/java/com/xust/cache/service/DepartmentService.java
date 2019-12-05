package com.xust.cache.service;

import com.xust.cache.bean.Department;
import com.xust.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Cacheable(value = "dept")
    public Department getDepartmentById(Integer id){
        Department dept = departmentMapper.getDeptById(id);
        return  dept;
    }
}
