package com.xust.mapper;

import com.xust.model.Employeeremove;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EmployeeremoveMapper {
    int deleteById(Integer id);

    int insert(Employeeremove employeeremove);

    int insertSelective(Employeeremove employeeremove);

    Employeeremove selectById(Integer id);

    int updateById(Employeeremove employeeremove);

    int updateByIdSelective(Employeeremove employeeremove);
}
