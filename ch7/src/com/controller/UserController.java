package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dao.UserDao;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;

	public void test() {
		
		System.out.println(userDao.selectUserById1(1));
		System.out.println("------------------------");
		System.out.println(userDao.selectUserById2(2));
		System.out.println("------------------------");
		System.out.println(userDao.selectUserById3(1));
	}
}
