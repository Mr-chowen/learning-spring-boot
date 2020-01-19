package com.xust.mapper;

import com.xust.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    List<User> findAll();

    User findById(long id);

    boolean insert(User user);

    boolean update(User user);

    boolean delete(long id);
}
