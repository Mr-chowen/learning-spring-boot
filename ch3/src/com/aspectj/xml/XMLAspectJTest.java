package com.aspectj.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dynamic.jdk.TestDao;

public class XMLAspectJTest {

	private static ApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("/com/aspectj/xml/applicationContext.xml");
		
		TestDao testDaoAdvice=(TestDao) appCon.getBean("testDao");
		testDaoAdvice.save();
	}

}
