package com.xust.controller;

import com.xust.entity.User;
import com.xust.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/list")
    public List<User> list(){
       List<User> users = userMapper.findAll();
       return users;
    }

    @RequestMapping("/get/{id}")
    public User get(@PathVariable("id") long id){
        User user = userMapper.findById(id);
        return user;
    }

    @RequestMapping("/add")
    public void add(User user){
        userMapper.insert(user);
    }

    @RequestMapping("/edit")
    public void edit(User user){
        userMapper.update(user);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id){
        userMapper.delete(id);
    }

}
