package com.service;
import com.po.User;
public interface UserService {
	boolean login(User user);
	boolean register(User user);
}
