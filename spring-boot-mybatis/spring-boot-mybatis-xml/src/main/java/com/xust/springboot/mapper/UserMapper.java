package com.xust.springboot.mapper;

import com.xust.springboot.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {

    List<Users> findAll();

    Users findById(long id);

    void add(Users users);

    void edit(Users users);

    void remove(long id);
}
