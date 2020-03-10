package com.xust.mapper;

import com.xust.model.AdjustSalary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AdjustSalaryMapper {
    int deleteById(Integer id);

    int insert(AdjustSalary adjustSalary);

    int insertSelective(AdjustSalary adjustSalary);

    AdjustSalary selectById(Integer id);

    int updateById(AdjustSalary adjustSalary);

    int updateByIdSelective(AdjustSalary adjustSalary);
}
