package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.annotation.AnnotationUser;

public class TestAnnotation {

	private static ApplicationContext appCon;

	public static void main(String[] args) {
		
		appCon = new ClassPathXmlApplicationContext("annotationContext.xml");
		
		AnnotationUser auser=(AnnotationUser) appCon.getBean("annotationUser");
		
		System.out.println(auser.getUname());
	}

}
