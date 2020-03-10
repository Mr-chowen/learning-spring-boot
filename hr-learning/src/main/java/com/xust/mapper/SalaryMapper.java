package com.xust.mapper;

import com.xust.model.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SalaryMapper {
    int deleteById(Integer id);

    int insert(Salary salary);

    int insertSelective(Salary salary);

    Salary selectById(Integer id);

    int updateById(Salary salary);

    int updateByIdSelective(Salary salary);

    List<Salary> getAllSalaries();
}
