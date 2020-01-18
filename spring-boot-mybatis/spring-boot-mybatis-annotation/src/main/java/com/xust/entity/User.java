package com.xust.entity;

import com.xust.enums.UserSexEnums;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private static final Long serialVersionUID=1L;

    private Long id;

    private String userName;

    private String passWord;

    private UserSexEnums userSex;

    private String nickName;

    public User(String userName, String passWord, UserSexEnums userSex, String nickName) {
        this.userName = userName;
        this.passWord = passWord;
        this.userSex = userSex;
        this.nickName = nickName;
    }
}
