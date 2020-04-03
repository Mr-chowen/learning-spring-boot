package com.xust.mall.mapper;

import com.xust.mall.model.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface AdminUserMapper {
    int insert(AdminUser adminUser);

    int insertSelective(AdminUser adminUser);

    AdminUser login(@Param("userName") String userName,@Param("passWord") String passWord);

    AdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser adminUser);

    int updateByPrimaryKey(AdminUser adminUser);

    int deleteByPrimaryKey(Integer id);
}
