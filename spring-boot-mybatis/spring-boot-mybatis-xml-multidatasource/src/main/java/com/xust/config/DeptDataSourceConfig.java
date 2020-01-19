package com.xust.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.xust.mapper.dept",sqlSessionTemplateRef="deptSqlSessionTemplate")
public class DeptDataSourceConfig {
    @Bean(name = "deptDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "deptSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("deptDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/dept/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "deptTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("deptDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "deptSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("deptSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
