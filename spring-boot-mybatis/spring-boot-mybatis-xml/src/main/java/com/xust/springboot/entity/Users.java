package com.xust.springboot.entity;

import com.xust.springboot.enums.UserSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@NoArgsConstructor
@ToString
public class Users implements Serializable {

    private static final Long serialVersionUID=1L;

    public Users(String userName, String passWord, UserSexEnum userSex, String nickName) {
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
