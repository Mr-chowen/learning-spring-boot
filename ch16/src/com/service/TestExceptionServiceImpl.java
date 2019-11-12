package com.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TestExceptionDao;
import com.exception.MyException;

@Service("testExceptionService")
public class TestExceptionServiceImpl implements TestExceptionService {
	
	@Autowired
	private TestExceptionDao testExceptionDao;
	
	@Override
	public void servicemy() throws Exception {
		// TODO Auto-generated method stub
		throw new MyException("service中自定义异常！");
	}

	@Override
	public void servicedb() throws Exception {
		// TODO Auto-generated method stub
		throw new SQLException("service中数据库异常！");
	}

	@Override
	public void daodb() throws Exception {
		// TODO Auto-generated method stub
		testExceptionDao.daodb();
	}

	@Override
	public void daomy() throws Exception {
		// TODO Auto-generated method stub
		testExceptionDao.daomy();
	}

	@Override
	public void serviceno() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("service 中未知异常！");
	}

	@Override
	public void daono() throws Exception {
		// TODO Auto-generated method stub
		testExceptionDao.daono();
	}

}
