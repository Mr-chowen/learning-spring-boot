package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dao.UserDao;
import com.po.User;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;
	public void test() {
		//	查询一个用户
		User auser =  userDao.selectUserById(1);
		System.out.println(auser);
		System.out.println("================");
		//	添加一个用户
		User addmu = new User();
		addmu.setUname("Grolla");
		addmu.setUsex("male");
		int add = userDao.addUser(addmu);
		System.out.println("添加了" + add + "条记录");
		System.out.println("================");
		//	修改一个用户
		User updatemu = new User();
		updatemu.setUid(1);
		updatemu.setUname("Tony");
		updatemu.setUsex("Male");
		int up = userDao.updateUser(updatemu);
		System.out.println("修改了" + up + "条记录");
		System.out.println( "================");
		//	删除一个用户
		int dl = userDao.deleteUser(9);
		System.out.println("删除了" + dl + "条记录");
		System.out.println("================");
		//	查询所有用户
		List<User> list = userDao.selectAllUser();
		for (User myUser : list) {
			System.out.println(myUser);
		}
	}
}
