package com.xust.mapper;

import com.xust.model.Appraise;
import com.xust.model.Employeeec;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EmployeeecMapper {
    int deleteById(Integer id);

    int insert(Appraise appraise);

    int insertSelective(Employeeec employeeec);

    Appraise selectById(Integer id);

    int updateById(Employeeec employeeec);

    int updateByIdSelective(Employeeec employeeec);

}
