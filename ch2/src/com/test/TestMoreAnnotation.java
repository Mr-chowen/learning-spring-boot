package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.annotation.controller.TestController;

public class TestMoreAnnotation {

	private static ApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("annotationContext.xml");
		
		TestController tc=(TestController) appCon.getBean("testController");
		tc.save();
	}

}
