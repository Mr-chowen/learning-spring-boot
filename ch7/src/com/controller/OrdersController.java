package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dao.OrdersDao;
import com.dao.UserDao;


@Controller
public class OrdersController {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrdersDao orderDao;
	
	
	public void test() {
		
		System.out.println(userDao.selectUserOrdersById1(1));
		System.out.println("--------------------------------");
		System.out.println(userDao.selectUserOrdersById2(1));
		System.out.println("--------------------------------");
		System.out.println(userDao.selectUserOrdersById3(1));
		
		System.out.println("--------------------------------");
		System.out.println(orderDao.selectallOrdersAndProducts());
	}
}
