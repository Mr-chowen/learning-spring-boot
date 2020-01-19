package com.xust.mapper.dept;

import com.xust.entity.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DeptMapper {
    @Select("select * from t_dept")
    @Results({
            @Result(property = "deptName",column = "deptName"),
            @Result(property = "deptLoca", column = "deptLoca")
    })
    List<Dept> findAll();

    @Select("select * from t_dept where id=#{id}")
    @Results({
            @Result(property = "deptName",column = "deptName"),
            @Result(property = "deptLoca", column = "deptLoca")
    })
    Dept findById(long id);

    @Insert("insert into t_dept(deptName,deptLoca) values(#{deptName},#{deptLoca})")
    void insert(Dept dept);

    @Update("update t_dept set deptName=#{deptName},deptLoca=#{deptLoca} where id=#{id}")
    void update(Dept dept);

    @Delete("delete from t_dept where id=#{id}")
    void delete(long id);

}
