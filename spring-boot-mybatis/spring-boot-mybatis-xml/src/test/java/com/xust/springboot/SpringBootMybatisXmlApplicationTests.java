package com.xust.springboot;

import com.xust.springboot.entity.Users;
import com.xust.springboot.enums.UserSexEnum;
import com.xust.springboot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class SpringBootMybatisXmlApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testAdd()throws Exception{
        userMapper.add(new Users("A1","a000000", UserSexEnum.MAN,"AA"));
        userMapper.add(new Users("B1","b000000", UserSexEnum.WOMAN,"BB"));
        userMapper.add(new Users("C1","c000000", UserSexEnum.MAN,"CC"));
    }

    @Test
    void testFindAll()throws Exception{
        List<Users> list=userMapper.findAll();
        if (list==null && list.size()==0)
            System.out.println("is null");
        else
            System.out.println(list.toString());
    }

    @Test
    void testEdit()throws Exception{
        Users user=userMapper.findById(28);
        System.out.println(user.toString());
        user.setNickName("tony");
        userMapper.edit(user);
        System.out.println(user.toString());
    }
}
