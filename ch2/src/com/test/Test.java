package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.BeanClass;
/**
 * spring框架实例化bean的方式有三种：
 * 1、构造方法实例化bean：spring容器调用bean对应类中的无参构造方法来实例化bean
 * 2、静态工厂实例化bean：在使用静态工厂实例化bean时要求开发者在工厂类中创建一个静态方法来创建bean的实例
 * 	在配置bean时，class属性指定静态工厂类，同时还需要使用factory-method属性指定工厂类的静态方法
 * 3、实例工厂实例化bean：在使用实例化bean时要求开发者在工厂类中创建一个实例方法来创建bean的实例
 * 	在配置bean时需要使用factory-bean属性指定配置的实例工厂，同时还需要使用factory-method属性指定实例工厂中的方法
 * @author kevin
 *
 */
public class Test {
	private static ApplicationContext appCon;

	public static void main(String[] args) {
		
		// 	初始化spring容器applicationContext，加载配置文件
		appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//	测试构造方法实例化bean
		BeanClass bc=(BeanClass) appCon.getBean("constructorInstance");
		
		//	测试静态工厂方法实例化bean
		BeanClass bc1 = (BeanClass) appCon.getBean("staticFactoryInstance");
		
		//	测试实例化工厂方法实例化bean
		BeanClass bc2 = (BeanClass) appCon.getBean("instanceFactoryInstance");
		
		System.out.println(bc+bc.message);
		System.out.println("-----------------");
		System.out.println(bc1+bc1.message);
		System.out.println("-----------------");
		System.out.println(bc2+bc2.message);
	}
	
}
