package com.xust.mapper;

import com.xust.model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface EmployeeMapper {

    int deleteById(Integer id);

    int insert(Employee employee);

    int insertSelective(Employee employee);

    Employee selectById(Integer id);

    int updateById(Employee employee);

    int updateByIdSelective(Employee employee);

    List<Employee> getEmployeeByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("emp") Employee employee,@Param("beginDateScope") Date[] beginDateScope);

    Long getAll(@Param("emp")Employee employee,@Param("beginDateScope")Date[] beginDateScope);

    Integer maxWorkID();

    Integer addEmployee(@Param("list") List<Employee> list);

    Employee getEmployeeById(Integer id);

    List<Employee> getEmployeeByPageWithSalary(@Param("page") Integer page,@Param("size") Integer size);

    Integer updateEmployeeSalaryById(@Param("eid") Integer eid,@Param("sid") Integer sid);
}
