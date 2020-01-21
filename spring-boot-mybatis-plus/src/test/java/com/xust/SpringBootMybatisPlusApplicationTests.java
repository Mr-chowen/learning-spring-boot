package com.xust;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xust.entity.Dept;
import com.xust.mapper.DeptMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootMybatisPlusApplicationTests {

    @Autowired
    DeptMapper deptMapper;

    @Test
    void testInsert(){
//        deptMapper.insert(new Dept(1L,"开发部","上海"));
        deptMapper.insert(new Dept(2L,"销售部","广东"));
    }

    @Test
    void testGetOne(){
//        System.out.println(deptMapper.selectById(1L));
        System.out.println(deptMapper.selectByName("销售部"));
    }

    @Test
    void testGetAll(){
        System.out.println(deptMapper.selectMaps(new QueryWrapper<>()));
    }

}
