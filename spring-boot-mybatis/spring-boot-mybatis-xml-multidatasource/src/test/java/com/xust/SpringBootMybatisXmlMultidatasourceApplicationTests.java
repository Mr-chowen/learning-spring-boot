package com.xust;

import org.junit.Assert;
import com.xust.entity.Dept;
import com.xust.entity.User;
import com.xust.enums.UserSexEnum;
import com.xust.mapper.dept.DeptMapper;
import com.xust.mapper.user.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootMybatisXmlMultidatasourceApplicationTests {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    void add()throws Exception{
        userMapper.insert(new User("tony","00000", UserSexEnum.MAN,"x"));
        deptMapper.insert(new Dept("财务部","上海"));

//        Assert.assertEquals(1,userMapper.findAll().size());
//        Assert.assertEquals(1,deptMapper.findAll().size());
    }

}
