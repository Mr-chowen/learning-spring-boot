package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.TransactionTemplateDao;

public class TransactionTemplateTest {
	private static ApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		TransactionTemplateDao ttd=(TransactionTemplateDao) appCon.getBean("transactionTemplateDao");
		
		System.out.println(ttd.test());
	}
	
	
	
}
