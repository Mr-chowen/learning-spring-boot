package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ITestService;
/**
 *	 在spring中实现IoC容器的方法是依赖注入，依赖注入的作用是在使用spring框架创建对象时动态的将其所依赖的对象注入bean组件中，spring框架的依赖注入通常有两种方式：
 * 	1、使用构造方法注入
 * 	2、使用setter注入
 * @author kevin
 *
 */
public class Test {

	private static ApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ITestService ts= (ITestService) appCon.getBean("testService");
		ITestService tt= (ITestService) appCon.getBean("testServiceSet");
		ts.sayHello();
		System.out.println("------------------");
		tt.sayHello();
	}

}
