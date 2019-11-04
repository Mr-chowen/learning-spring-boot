package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.pojo.SelectUserById;
import com.pojo.SelectUserOrderById;
import com.pojo.User;

@Repository
@Mapper
public interface UserDao {
	
	public User selectUserById1(Integer uid);
	public User selectUserById2(Integer uid);
	public SelectUserById selectUserById3(Integer uid);
	
	public User selectUserOrdersById1(Integer uid);
	public User selectUserOrdersById2(Integer uid);
	public List<SelectUserOrderById> selectUserOrdersById3(Integer uid);
}
