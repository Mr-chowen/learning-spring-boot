package com.xust.mall.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class AdminUser {
    private Integer adminUserId;

    private String loginUserName;

    private String loginPassWord;

    private String nickName;

    private Byte locked;
}
