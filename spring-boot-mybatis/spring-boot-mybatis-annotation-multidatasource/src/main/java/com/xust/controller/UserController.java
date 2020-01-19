package com.xust.controller;

import com.xust.entity.User;
import com.xust.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user/list")
    public List<User> list(){
        List<User> users = userMapper.findAll();
        return users;
    }

    @RequestMapping("/user/get/{id}")
    public User get(@PathVariable("id") long id){
        User user = userMapper.findById(id);
        return user;
    }

    @RequestMapping("/user/add")
    public void add(User user){
        userMapper.insert(user);
    }

    @RequestMapping("/user/edit")
    public void edit(User user){
        userMapper.update(user);
    }

    @RequestMapping("/user/remove/{id}")
    public void remove(@PathVariable("id") long id){
        userMapper.delete(id);
    }
}
