package com.xust.task.controller;

import com.xust.task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {
    @Autowired
    AsyncService helloService;
    @GetMapping("/hello")
    public String hello(){
        helloService.hello();
        return "success";
    }
}
