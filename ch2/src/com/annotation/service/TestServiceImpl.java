package com.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annotation.dao.TestDao;

@Service("testService")
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDao testDao;
	
	@Override
	public void save() {
		testDao.save();
		System.out.println("service say yes you can do it !");
	}

}
