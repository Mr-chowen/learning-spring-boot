package com.service;

import com.dao.ITestDao;

public class ITestServiceImpl implements ITestService {

	private ITestDao itestDao;
	
	//	构造方法，用于实现依赖注入接口对象itestDao;
	public ITestServiceImpl(ITestDao itestDao) {
		super();
		this.itestDao = itestDao;
	}

	
	@Override
	public void sayHello() {
		//	调用itestDao中的sayHello方法
		itestDao.sayHello();
		System.out.println("构造方法注入 say:Hello , good study !");
	}

}
