package com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.pojo.SelectUserById;
import com.pojo.User;

@Repository
@Mapper
public interface UserDao {
	public User selectUserById1(Integer uid);
	public User selectUserById2(Integer uid);
	public SelectUserById selectUserById3(Integer uid);
}
