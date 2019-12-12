package com.xust.springcloud.service;

import com.xust.springcloud.dao.DeptDao;
import com.xust.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {
    @Autowired
    private DeptDao deptDao;

    public boolean add(Dept dept){
       return deptDao.addDept(dept);
    }

    public boolean delete(Long id){
        return  deptDao.deleteDept(id);
    }

    public boolean update(Dept dept){
        return  deptDao.updateDept(dept);
    }

    public Dept get(Long id){
        return  deptDao.findById(id);
    }

    public List<Dept> list(){
        return  deptDao.findAll();
    }
}
