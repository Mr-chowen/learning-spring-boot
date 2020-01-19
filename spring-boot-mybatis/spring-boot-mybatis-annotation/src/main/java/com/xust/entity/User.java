package com.xust.entity;

import com.xust.enums.UserSexEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
@NoArgsConstructor
public class User implements Serializable {

    private static final Long serialVersionUID=1L;

    public User(String userName, String passWord, UserSexEnum userSex, String nickName) {
        this.userName = userName;
        this.passWord = passWord;
        this.userSex = userSex;
        this.nickName = nickName;
    }

    private Long id;

    private String userName;

    private String passWord;

    private UserSexEnum userSex;

    private String nickName;
}
