package com.xust.springcloud.service.impl;

import com.xust.springcloud.dao.DeptDao;
import com.xust.springcloud.entities.Dept;
import com.xust.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptDao deptDao;

    @Override
    public boolean addDept(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public Long deleteDept(Long id) {
        return deptDao.deleteDept(id);
    }

    @Override
    public boolean updateDept(Dept dept) {
        return deptDao.updateDept(dept);
    }

    @Override
    public Dept get(Long id) {
        return deptDao.findById(id);
    }

    @Override
    public List<Dept> list() {
        return deptDao.findAll();
    }
}
