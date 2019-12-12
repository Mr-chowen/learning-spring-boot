package com.xust.springcloud.dao;

import com.xust.springcloud.entities.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DeptDao {

    @Insert("insert into department(dname,dsource) values(#{dname},#{dsource})")
    boolean addDept(Dept dept);

    @Delete("delete from department where id=#{id}")
    boolean deleteDept(Long id);

    @Update("update department set dname=#{dname},dsource=#{dsource} where id=#{id}")
    boolean updateDept(Dept dept);

    @Select("select * from department where id=#{id}")
    Dept findById(Long id);

    @Select("select * from department")
    List<Dept> findAll();
}
