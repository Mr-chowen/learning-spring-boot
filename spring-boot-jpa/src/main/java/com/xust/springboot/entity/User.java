package com.xust.springboot.entity;

import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //  告诉JPA这是一个实体类(和数据表是映射的)
@Table(name="t_user")//@Table来指定和哪个数据表对应，如果省略默认是user（即类名的小写）
public class User {
    @Id //标注这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//设置主键自增
    private Integer uid;

    @Column(name="last_name",length = 50)//这是和数据表对应的列
    private String lastName;

    @Column //省略列名默认为属性名
    private String email;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
