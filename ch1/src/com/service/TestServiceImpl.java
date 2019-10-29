package com.service;

import com.dao.ITestDao;

public class TestServiceImpl implements ITestService {
	
	private ITestDao itestDao;
	//	使用 setter方法注入
	public void setItestDao(ITestDao itestDao) {
		this.itestDao = itestDao;
	}

	@Override
	public void sayHello() {
		itestDao.sayHello();
		System.out.println("setter方法注入 say： you're the best !");
	}

}
