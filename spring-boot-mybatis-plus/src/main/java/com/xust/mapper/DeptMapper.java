package com.xust.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xust.entity.Dept;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface DeptMapper extends BaseMapper<Dept> {
    /**
     * 自定义查询方法
     * @param deptName
     * @return
     */
    @Select("select * from t_dept where dept_name=#{dept_name}")
    @Results({
            @Result(property = "dept_name",column = "dept_name"),
            @Result(property = "dept_local",column = "dept_local")
    })
    Dept selectByName(String deptName);

}
