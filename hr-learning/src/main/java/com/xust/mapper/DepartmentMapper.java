package com.xust.mapper;

import com.xust.model.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DepartmentMapper {
    int deleteById(Integer id);

    int insert(Department department);

    int insertSelective(Department department);

    Department selectById(Integer id);

    int updateById(Department department);

    int updateByIdSelective(Department department);

    List<Department> getAllDepartmentsByParentId(Integer id);

    void deleteDepartmentById(Department department);

    void addDepartment(Department department);

    List<Department> getAllDepartmentWithOutChildren();


}
