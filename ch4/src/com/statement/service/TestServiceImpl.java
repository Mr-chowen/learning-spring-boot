package com.statement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statement.dao.TestDao;



@Service("testService")
@Transactional(rollbackFor = {Exception.class})		//	@Transactional只针对public属性范围内的方法添加
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDao testDao;
	
	@Override
	public void test() {
		String deleteSql="delete from user";
		String insertSql="insert into user values(?,?,?)";
		Object param[]= {1,"Dony","male"};
		testDao.delete(deleteSql, null);
		//	插入两条相同数据
		testDao.save(insertSql, param);
		testDao.save(insertSql, param);
	}

}
