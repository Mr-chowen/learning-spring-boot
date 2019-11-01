package com.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.po.User;

public class MyBatisTest {

	public static void main(String[] args) {
		
		try {
			//	读取配置文件mybatis-config.xml
			InputStream config=Resources.getResourceAsStream("mybatis-config.xml");
			//	根据配置文件构建SqlSessionFactory
			SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(config);
			//	通过SqlSessionFactory创建SqlSession
			SqlSession ss=ssf.openSession();
			
			//	查询一个用户
			User user=ss.selectOne("com.mybatis.mapper.UserMapper.selectUserById",1);
			System.out.println(user);
			
			//	添加一个用户
			User addUser=new User();
			addUser.setUname("Tony");
			addUser.setUsex("male");
			ss.insert("com.mybatis.mapper.UserMapper.addUser", addUser);
			
			//	修改一个用户
			User updateUser=new User();
			updateUser.setUid(1);
			updateUser.setUname("Alice");
			updateUser.setUsex("female");
			ss.update("com.mybatis.mapper.UserMapper.updateUser",updateUser);
			
			//	删除一个用户
			ss.delete("com.mybatis.mapper.UserMapper.deleteUser",2);
			//	查询所有用户
			List<User> list=ss.selectList("com.mybatis.mapper.UserMapper.selectAllUser");
			for (User user2 : list) {
				System.out.println(user2);
			}
			
			//	提交事务
			ss.commit();
			//	关闭SqlSession
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
