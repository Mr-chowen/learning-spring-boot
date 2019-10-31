package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.UserDao;
import com.pojo.MyUser;

public class SpringJdbcTest {
	private static ApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserDao userDao=(UserDao) appCon.getBean("userDao");
		String insertSql="insert into user values(null,?,?)";
		Object param1[]= {"tony","male"};
		Object param2[]= {"join","female"};
		Object param3[]= {"semb","male"};
		Object param4[]= {"faker","female"};
		//	添加用户
		userDao.update(insertSql, param1);
		userDao.update(insertSql, param2);
		userDao.update(insertSql, param3);
		userDao.update(insertSql, param4);
		
		//	删除用户
			String deleteSql="delete from user where uid=?";
			Object param[]= {1};
			userDao.update(deleteSql, param);
		
		//	查询用户
		String query="select * from user";
		List<MyUser> list=userDao.query(query, null);
		for (MyUser myUser : list) {
			System.out.println(myUser);
		}
		
	}
}
