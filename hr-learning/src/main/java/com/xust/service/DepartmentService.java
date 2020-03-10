package com.xust.service;

import com.xust.mapper.DepartmentMapper;
import com.xust.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments(){
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public void addDepartment(Department department){
        department.setEnabled(true);
        departmentMapper.insert(department);
    }

    public void deleteDepartmentById(Department department){
        department.setEnabled(true);
        departmentMapper.deleteDepartmentById(department);
    }

    public List<Department> getAllDepartmentWithOutChildren(){
        return departmentMapper.getAllDepartmentWithOutChildren();
    }

    public int updateDepartment(Department department){
        return departmentMapper.updateByIdSelective(department);
    }
}
