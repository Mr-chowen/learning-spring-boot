package com.statement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository("testDao")
public class TeestDaoImpl implements TestDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(String sql, Object[] param) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(sql, param);
	}

	@Override
	public int delete(String sql, Object[] param) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(sql, param);
	}

}
