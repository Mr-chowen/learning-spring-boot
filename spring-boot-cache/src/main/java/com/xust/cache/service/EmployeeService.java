package com.xust.cache.service;

import com.xust.cache.bean.Employee;
import com.xust.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
@CacheConfig(cacheNames = "emp")//抽取缓存的公共配置
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将运行结果进行缓存，以后获取直接从换从中获取，不用调用方法
     * Cacheable几个重要的属性：
     *          cacheNames/value:缓存的名字
     *          key:缓存数据使用的key，可以使用他来指定，默认使用方法参数的值，比如1-方法的返回值,
     *              可以使用spEL来编写eg:#id(参数id的值)等同于#a0 、#p0、 #root.args[0]
     *          keyGenerator：主键key生成器,可以自己指定key的生成器的组件id
     *          key和keyGenerator二选一使用
     *          cacheManager：指定缓存管理器，或者使用cacheResolver指定缓存解析器（二选一使用即可）
     *          condition：指定符合条件的情况下进行缓存，可以使用spEL表达式
     *          unless:当指定的条件为true，方法的返回值就不会缓存，可以获取到结果进行判断eg:unless = "#result == null"
     *          sync:是否使用异步模式
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "emp")
    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut既调用方法也更新缓存数据
     * 修改了数据库的某条数据，同时更新了缓存
     * @param employee
     * @return
     */
    @CachePut(value = "emp")
    public Employee updateEmployee(Employee employee){
        System.out.println("update: "+employee.toString());
        employeeMapper.updateEmp(employee);
        return  employee;
    }

    /**
     * @CacheEvict清除缓存
     * key可以清除指定缓存eg:key = "#id"
     * allEntries可以清除所有缓存，eg:allEntries = true
     * beforeInvocation=false:缓存的清除是否在方法之前执行
     *  默认缓存清除操作是在方法执行之后执行,如果出现异常缓存就不会清除
     * @param id
     */
    @CacheEvict(value = "emp")
    public Integer deleteEmp(Integer id){
        System.out.println("deleteEmp:"+id);
//        employeeMapper.deleteEmp(id);
        return id;
    }
    //@Caching定义复杂的缓存
    @Caching(
           cacheable =  {
                   @Cacheable(value = "emp",key = "#lastName")
           },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
       return employeeMapper.getEmpByLastName(lastName);
    }
}
