package com.xust.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xust.mall.mapper")
public class MyBatisConfig {

}
