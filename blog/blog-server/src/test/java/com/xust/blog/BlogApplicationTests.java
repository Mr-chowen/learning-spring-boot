package com.xust.blog;

//import cn.hutool.crypto.SecureUtil;
//import com.xust.blog.entity.User;
import com.xust.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import java.time.LocalDateTime;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("demo");
//        user.setPassword(SecureUtil.md5("000000"));
//        user.setAvatar("demo");
//        user.setEmail("demo@163.com");
//        user.setStatus(0);
//        user.setCreated(LocalDateTime.now());
//        user.setLastLogin(LocalDateTime.now().plusMonths(1));
//        userService.save(user);
//        System.out.println(userService.getById(3L));
    }

}
