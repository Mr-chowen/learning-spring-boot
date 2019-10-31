package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Repository("codeTransaction")
public class CodeTransaction {
	//	使用配置文件的JDBC模板
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSourceTransactionManager txManager;
	
	public String test() {
		//	默认事务定义，例如隔离级别、传播行为等
		TransactionDefinition tf=new DefaultTransactionDefinition();
		//	开启事务
		TransactionStatus ts=txManager.getTransaction(tf);
		
		String message="执行成功，没有事务回滚！";
		
		try {
			//	删除表中数据
			String deleteSql="delete from user ";
			//	添加数据
			String insertSql="insert into user values(?,?,?)";
			Object param[] = {1,"white","male"};
			//	删除一条数据
			jdbcTemplate.update(deleteSql);
			//	添加一条数据
			jdbcTemplate.update(insertSql,param);
			//	提交事务
			txManager.commit(ts);
		} catch (Exception e) {
			//	出现异常，事务回滚
			txManager.rollback(ts);
			message="主键重复，事务回滚！";
			e.printStackTrace();
		} 
		return message;
		
	}

}
