package com.xust.blog.utils;

import com.xust.blog.shiro.UserProfile;
import org.apache.shiro.SecurityUtils;

import java.io.Serializable;

public class ShiroUtil implements Serializable {

    public static UserProfile getProfile(){
        return (UserProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
