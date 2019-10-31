package com.dao;

import java.util.List;

import com.pojo.MyUser;

public interface UserDao {
	public int update(String sql,Object[] param);
	public List<MyUser> query(String sql,Object[] param);
}
