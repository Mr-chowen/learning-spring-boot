package com.spring.proxyfactorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dynamic.jdk.TestDao;

public class ProxyFactoryBeanTest {
	private static ApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("/com/spring/proxyfactorybean/applicationContext.xml");
		
		TestDao testDaoAdvice=(TestDao) appCon.getBean("testDaoProxy");	
		
		//	执行方法
		testDaoAdvice.save();
		System.out.println("---------------");
		testDaoAdvice.modify();;
		System.out.println("---------------");
		testDaoAdvice.delete();
		System.out.println("---------------");
	}
}