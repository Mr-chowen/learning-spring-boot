package com.xust.springboot.mapper;

import com.xust.springboot.bean.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

//一定要使用@Mapper或者批量@MapperScan(value="mapper映射包地址")
public interface EmployeeMapper {


    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);

}
