package com.xust.mapper;

import com.xust.model.Employeeremove;
import com.xust.model.Employeetrain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EmployeetrainMapper {
    int deleteById(Integer id);

    int insert(Employeetrain employeetrain);

    int insertSelective(Employeetrain employeetrain);

    Employeeremove selectById(Integer id);

    int updateById(Employeetrain employeetrain);

    int updateByIdSelective(Employeetrain employeetrain);
}
