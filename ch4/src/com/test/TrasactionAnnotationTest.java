package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.statement.controller.TestController;

public class TrasactionAnnotationTest {

	private static ApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("annotationstatementapplicationContext.xml");
		
		TestController tc=(TestController) appCon.getBean("testController");
		tc.test();
	
	}

}
