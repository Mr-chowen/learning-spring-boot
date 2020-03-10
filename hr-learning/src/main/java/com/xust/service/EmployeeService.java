package com.xust.service;


import com.xust.common.util.PageBean;
import com.xust.mapper.EmployeeMapper;
import com.xust.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public final static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    public PageBean getEmployeeByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope){
        if(page != null && size != null){
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page,size,employee,beginDateScope);
        Long total = employeeMapper.getAll(employee,beginDateScope);
        PageBean bean = new PageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmployee(Employee employee){
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        int result = employeeMapper.insertSelective(employee);
        if (result == 1){
            Employee emp = employeeMapper.getEmployeeById(employee.getId());
            LOGGER.info(emp.toString());
            rabbitTemplate.convertAndSend("welcome",emp);
        }
        return result;
    }

    public Integer maxWorkID(){
        return employeeMapper.maxWorkID();
    }

    public Integer deleteEmployeeByEid(Integer id){
        return employeeMapper.deleteById(id);
    }

    public Integer updateEmployee(Employee employee){
        return employeeMapper.updateByIdSelective(employee);
    }

    public Integer addEmployees(List<Employee> list){
        return employeeMapper.addEmployee(list);
    }

    public PageBean getEmployeeByPageWithSalary(Integer page,Integer size){
        if(page != null && size != null){
            page = (page - 1) * size;
        }
        List<Employee> list = employeeMapper.getEmployeeByPageWithSalary(page,size);
        PageBean pageBean = new PageBean();
        pageBean.setData(list);
        pageBean.setTotal(employeeMapper.getAll(null,null));
        return pageBean;
    }

    public Integer updateEmployeeSalaryById(Integer eid,Integer sid){
        return employeeMapper.updateEmployeeSalaryById(eid,sid);
    }
}
