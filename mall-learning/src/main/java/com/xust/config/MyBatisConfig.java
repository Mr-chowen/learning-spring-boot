package com.xust.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.xust.mbg.mapper","com.xust.dao"})
public class MyBatisConfig {
}
