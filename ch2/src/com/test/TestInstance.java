package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.BeanClass;

public class TestInstance {
	
	private static ApplicationContext appCon;

	public static void main(String[] args) {
		
		appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 	测试bean的作用域scope="singleton"，在spring容器中只有一个bean实例
		BeanClass b1=(BeanClass) appCon.getBean("constructorInstance");
		System.out.println(b1);
		BeanClass b2=(BeanClass) appCon.getBean("constructorInstance");
		System.out.println(b2);
	}
	
}
