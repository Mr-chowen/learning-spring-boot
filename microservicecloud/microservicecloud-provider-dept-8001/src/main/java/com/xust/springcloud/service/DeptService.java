package com.xust.springcloud.service;

import com.xust.springcloud.entities.Dept;

import java.util.List;

public interface DeptService {

    public boolean addDept(Dept dept);

    public Long deleteDept(Long id);

    public boolean updateDept(Dept dept);

    public Dept get(Long id);

    public List<Dept> list();

}
