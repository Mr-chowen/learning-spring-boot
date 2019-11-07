package com.servcie;

import java.util.ArrayList;

import com.po.User;

public interface UserServcie {
	public boolean addUser(User u);
	public ArrayList<User> getUsers();
}
