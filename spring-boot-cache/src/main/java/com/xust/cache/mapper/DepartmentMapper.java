package com.xust.cache.mapper;

import com.xust.cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface DepartmentMapper {
    @Select("select *  from department where id=#{id}")
    public Department getDeptById(Integer id);
}
