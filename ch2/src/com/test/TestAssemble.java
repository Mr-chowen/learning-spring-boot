package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.assemble.ComplexUser;

public class TestAssemble {

	private static ApplicationContext appCon;

	public static void main(String[] args) {
		
		appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		//	测试使用构造方法装配测试
		ComplexUser u1 = (ComplexUser) appCon.getBean("auser");
		System.out.println(u1);
		
		//	测试使用setter方法装配测试
		ComplexUser u2 = (ComplexUser) appCon.getBean("buser");
		System.out.println(u2);
		
	}

}
