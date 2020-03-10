package com.xust.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.xust.mapper")
public class MyBatisConfig {

}
