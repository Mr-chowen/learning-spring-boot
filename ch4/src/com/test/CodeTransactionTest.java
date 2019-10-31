package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.CodeTransaction;

public class CodeTransactionTest {

	private static ApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		CodeTransaction ct=(CodeTransaction) appCon.getBean("codeTransaction");
		
		System.out.println(ct.test());
	}

}
