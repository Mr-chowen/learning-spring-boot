package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pojo.MyUser;
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*
	 * 	更新方法包括：添加、修改、删除
	 * 	param为sql中的参数，例如通配符？
	 */
	@Override
	public int update(String sql, Object[] param) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(sql,param);
	}
	/*
	 * 	查询方法
	 * 	param为sql中的参数，例如通配符？
	 */
	@Override
	public List<MyUser> query(String sql, Object[] param) {
		RowMapper<MyUser> rowMapper=new BeanPropertyRowMapper<MyUser>(MyUser.class);
		return jdbcTemplate.query(sql, rowMapper,param);
	}

}
