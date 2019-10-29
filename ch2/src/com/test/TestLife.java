package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.life.BeanLife;

public class TestLife {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		System.out.println("获得对象前");
		BeanLife beanLife = (BeanLife) appCon.getBean("beanLife");
		System.out.println("获得对象后"+beanLife);
		//	关闭容器销毁bean对象
		appCon.close();

	}

}
