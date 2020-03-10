package com.xust.service;

import com.xust.mapper.SalaryMapper;
import com.xust.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalaryService {
    @Autowired
    SalaryMapper salaryMapper;

    public List<Salary> getAllSalaries(){
        return salaryMapper.getAllSalaries();
    }

    public Integer addSalary(Salary salary){
        salary.setCreateDate(new Date());
        return salaryMapper.insert(salary);
    }

    public Integer deleteSalaryById(Integer id){
        return salaryMapper.deleteById(id);
    }

    public Integer updateSalaryById(Salary salary){
        return salaryMapper.updateByIdSelective(salary);
    }
}
