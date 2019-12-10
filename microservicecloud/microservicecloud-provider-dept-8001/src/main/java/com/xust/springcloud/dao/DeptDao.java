package com.xust.springcloud.dao;

import com.xust.springcloud.entities.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptDao {

    @Insert("insert into department(dname,dsource) values(#{dname},#{dsource}) ")
    public boolean addDept(Dept dept);

    @Delete("delete from department where id=#{id}")
    public Long deleteDept(Long id);

    @Update("update department set dname=#{dname},dsource=#{dsource} where id=#{id}")
    public boolean updateDept(Dept dept);

    @Select("select * from department where id=#{id}")
    public Dept findById(Long id);

    @Select("select * from department")
    public List<Dept> findAll();
}
