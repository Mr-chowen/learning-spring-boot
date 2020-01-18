package com.xust;

import com.xust.entity.User;
import com.xust.enums.UserSexEnums;
import com.xust.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootMybatisAnnotationApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void testInsert()throws Exception{
        userMapper.insert(new User("martin","000000", UserSexEnums.MAN,"aa"));
    }

    @Test
    void testEdit()throws Exception{
        User user = userMapper.findById(29);
        System.out.println(user.toString());
        user.setUserName("Martin");
        user.setUserSex(UserSexEnums.MAN);
        userMapper.update(user);
        System.out.println(user.toString());
    }
}
