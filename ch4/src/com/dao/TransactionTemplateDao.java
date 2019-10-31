package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Repository("transactionTemplateDao")
public class TransactionTemplateDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TransactionTemplate transactionTemplate;
	String  message="";
	
	public String test() {
		
		transactionTemplate.execute(new TransactionCallback<Object>() {

			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				// 删除表中数据
				String deleteSql="delete from user";
				//	添加数据
				String insertSql="insert into user values(?,?,?)";
				
				Object param[]= {1,"Tom","female"};
				
				try {
					jdbcTemplate.update(deleteSql);
					
					jdbcTemplate.update(insertSql,param);
					//	添加同一条数据，使主键重复
					jdbcTemplate.update(insertSql,param);
					
					message = "执行成功，没有事务回滚！";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					message = "主键重复，事务回滚！";
					e.printStackTrace();
				}
				return message;
			}
			
		});
		
		return message;
	}
}
