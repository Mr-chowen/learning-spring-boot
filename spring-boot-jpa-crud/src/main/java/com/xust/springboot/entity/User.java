package com.xust.springboot.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,unique = true,length = 50)
    private String userName;

    @Column(nullable = false,length = 50)
    private String password;

    @Column(nullable = false,length = 11)
    private int age;

}
