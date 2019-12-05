package com.xust.springboot.mapper;

import com.xust.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(dname) values(#{dname})")
    public int insertDept(Department department);

    @Update("update department set dname=#{dname} where id=#{id}")
    public int updateDept(Department department);
}
