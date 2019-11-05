package com.service;
import org.springframework.stereotype.Service;
import com.po.User;

@Service
public class UserServiceImpl implements UserService{
	@Override
	public boolean login(User user) {
		if("zhangsan".equals(user.getUname()) 
				&& "123456".equals(user.getPassword()))
			return true;
		return false;
	}
	@Override
	public boolean register(User user) {
		if("zhangsan".equals(user.getUname()) 
				&& "123456".equals(user.getPassword()))
			return true;
		return false;
	}
}

