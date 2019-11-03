package com.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestController {
	private static ApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserController uc=(UserController) appCon.getBean("userController");
	
		uc.test();

	}
}
