package com.xust.mapper.user;

import com.xust.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserMapper {

    List<User> findAll();

    User findById(long id);

    void insert(User user);

    void update(User user);

    void delete(long id);
}
