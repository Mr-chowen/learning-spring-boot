package com.xust.mapper.user;

import com.xust.entity.User;
import com.xust.enums.UserSexEnum;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserMapper {
    @Select("select * from t_user")
    @Results({
            @Result(property = "userSex",column = "userSex",javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nickName")
    })
    List<User> findAll();

    @Select("select * from t_user where id=#{id}")
    @Results({
            @Result(property = "userSex",column = "userSex",javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nickName")
    })
    User findById(long id);

    @Insert("insert into t_user(userName,passWord,userSex,nickName) values(#{userName},#{passWord},#{userSex},#{nickName})")
    void insert(User user);

    @Update("update t_user set userName=#{userName},nickName=#{nickName} where id=#{id}")
    void update(User user);

    @Delete("delete from t_user where id=#{id}")
    void delete(long id);
}
