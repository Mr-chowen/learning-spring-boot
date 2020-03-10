package com.xust.mapper;

import com.xust.model.EmpSalary;
import com.xust.model.Employeeremove;
import com.xust.model.Employeetrain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EmpSalaryMapper {
    int deleteById(Integer id);

    int insert(EmpSalary empSalary);

    int insertSelective(EmpSalary empSalary);

    Employeeremove selectById(Integer id);

    int updateById(EmpSalary empSalary);

    int updateByIdSelective(EmpSalary empSalary);
}
