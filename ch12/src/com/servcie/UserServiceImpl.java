package com.servcie;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.po.User;
@Service
public class UserServiceImpl implements UserServcie {

	private static ArrayList<User> users=new ArrayList<User>();
	
	@Override
	public boolean addUser(User u) {
		// TODO Auto-generated method stub
		if (!"IT民工".equals(u.getCarrer())) {	// 	不允许添加IT民工
			users.add(u);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<User> getUsers() {
		// TODO Auto-generated method stub
		return users;
	}

}
