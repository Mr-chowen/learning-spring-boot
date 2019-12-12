package com.xust.springcloud.controller;

import com.xust.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController_Consumer {

    private static  final  String REST_URL_PREFIX="http://localhost:8080";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/dept/add")
    boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/delete/{id}")
    boolean delete(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/delete/"+id,Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/update")
    boolean update(Dept dept){
        return  restTemplate.postForObject(REST_URL_PREFIX+"/dept/update",dept,Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    Dept get(@PathVariable("id") Long id){
        return  restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }

    @RequestMapping(value = "/consumer/dept/list")
    List<Dept> list(){
        return  restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }
}
