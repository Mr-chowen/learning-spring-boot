package com.controller;

import java.util.ArrayList;
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
		User userA=new User();
		userA.setUname("冬");
		userA.setUsex("男");
		List<User> listA=userDao.selectUserByIf(userA);
		System.out.println("if元素---------------------");
		for (User user1 : listA) {
			System.out.println(user1);
		}
		
		User userB=new User();
		userB.setUname("");
		userB.setUsex("");
		List<User> listB=userDao.selectUserByChoose(userB);
		System.out.println("choose元素-------------------");
		for (User user2 : listB) {
			System.out.println(user2);
		}
		
		User userC=new User();
		userC.setUname("夏");
		userC.setUsex("男");
		List<User> listC=userDao.selectUserByTrim(userC);
		System.out.println("trim元素-------------------");
		for (User user3 : listC) {
			System.out.println(user3);
		}
		
		
		User userD=new User();
		userD.setUname("秋");
		userD.setUsex("女");
		List<User> listD=userDao.selectUserByWhere(userD);
		System.out.println("where元素-------------------");
		for (User user4 : listD) {
			System.out.println(user4);
		}
		
		User userE=new User();
		userE.setUid(2);
		userE.setUname("春生");
		userE.setUsex("女");
		System.out.println("修改了"+userDao.updateUserBySet(userE)+"条记录");
		
		List<Integer> listId=new ArrayList<Integer>();
		listId.add(2);
		listId.add(3);
		List<User> listByForeach=userDao.selectUserByForeach(listId);
		System.out.println("forEach元素----------------------------");
		for (User user : listByForeach) {
			System.out.println(user);
		}
		
		User userF=new User();
		userF.setUname("晨");
		List<User> listByBind=userDao.selectUserByBind(userF);
		System.out.println("bind元素------------------------");
		for (User user : listByBind) {
			System.out.println(user);
		}
	}
}
