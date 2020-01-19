package com.xust;

import com.xust.entity.User;
import com.xust.enums.UserSexEnum;
import com.xust.mapper.UserMapper;
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
        userMapper.insert(new User("A1","000000", UserSexEnum.MAN,"AA"));
        userMapper.insert(new User("B1","000000", UserSexEnum.WOMAN,"BB"));
        userMapper.insert(new User("C1","000000", UserSexEnum.MAN,"CC"));
    }

    @Test
    void testFindAll()throws Exception{
        List<User> users=userMapper.findAll();
        if (users==null && users.size()==0)
            System.out.println("is null");
        else
            System.out.println(users.toString());
    }

    @Test
    void testEdit()throws Exception{
        User user=userMapper.findById(28);
        System.out.println(user.toString());
        user.setNickName("tony");
        userMapper.update(user);
        System.out.println(user.toString());
    }
}
