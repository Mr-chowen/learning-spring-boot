package com.xust.springboot.controller;

import com.xust.springboot.entity.User;
import com.xust.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @GetMapping("/user")
    public User save(User user){
        User u=userRepository.save(user);
        return u;
    }
}
