package com.xust;

import com.xust.model.Role;
import com.xust.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrLearningApplicationTests {
    @Autowired
    RoleService roleService;

    @Test
    public void contextLoads() {
//        Role role = new Role();
//        role.setId(1);
//        role.setName("0000");
//        role.setNameZh("0000");
//        System.out.println(roleService.addRole(role));
    }

    @Test
    public void demo(){

    }

}
