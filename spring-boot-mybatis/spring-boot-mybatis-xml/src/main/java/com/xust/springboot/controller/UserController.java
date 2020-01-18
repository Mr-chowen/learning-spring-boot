package com.xust.springboot.controller;

import com.xust.springboot.entity.Users;
import com.xust.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/list")
    public List<Users> list(){
        List<Users> users = userMapper.findAll();
        return users;
    }

    @RequestMapping("/get")
    public Users get(long id){
        Users user = userMapper.findById(id);
        return user;
    }

    @RequestMapping("/insert")
    public void insert(Users users){
        userMapper.add(users);
    }

    @RequestMapping("/update")
    public void update(Users users){
        userMapper.edit(users);
    }

    @RequestMapping("/delete")
    public void delete(long id){
        userMapper.remove(id);
    }

}
