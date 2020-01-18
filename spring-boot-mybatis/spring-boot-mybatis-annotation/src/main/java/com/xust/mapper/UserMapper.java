package com.xust.mapper;

import com.xust.entity.User;
import com.xust.enums.UserSexEnums;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from t_users")
    @Results({
            @Result(property = "userSex",column = "userSex",javaType = UserSexEnums.class),
            @Result(property = "nickName", column = "nickName")
    })
    List<User> findAll();

    @Select("select * from t_users where id=#{id}")
    @Results({
            @Result(property = "userSex",column = "userSex",javaType = UserSexEnums.class),
            @Result(property = "nickName", column = "nickName")
    })
    User findById(long id);

    @Insert("insert into t_users(userName,passWord,userSex,nickName) values(#{userName},#{passWord},#{userSex},#{nickName})")
    void insert(User user);

    @Update("update t_users set userName=#{userName},nickName=#{nickName} where id=#{id}")
    void update(User user);

    @Delete("delete from t_users where id=#{id}")
    void delete(long id);
}
