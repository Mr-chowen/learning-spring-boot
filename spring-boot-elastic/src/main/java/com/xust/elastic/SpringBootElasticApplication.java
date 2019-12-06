package com.xust.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot支持两种技术来和ES交互：
 * 1、Jest（默认不生效）
 * 2、springdata elasticsearch
 */
@SpringBootApplication
public class SpringBootElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootElasticApplication.class, args);
    }

}
