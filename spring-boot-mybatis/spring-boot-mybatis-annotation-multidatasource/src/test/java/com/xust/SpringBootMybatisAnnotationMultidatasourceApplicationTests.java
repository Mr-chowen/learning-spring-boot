package com.xust;

import com.xust.entity.Dept;
import com.xust.entity.User;
import com.xust.enums.UserSexEnum;
import com.xust.mapper.dept.DeptMapper;
import com.xust.mapper.user.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootMybatisAnnotationMultidatasourceApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Test
    void add()throws Exception {
        userMapper.insert(new User("martin","00000", UserSexEnum.MAN,"aa"));
        deptMapper.insert(new Dept("技术部","北京"));
    }

}
