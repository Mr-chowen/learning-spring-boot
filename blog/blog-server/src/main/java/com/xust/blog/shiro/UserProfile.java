package com.xust.blog.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserProfile implements Serializable {
    private Long id;

    private String username;

    private String avatar;

    private String email;
}
