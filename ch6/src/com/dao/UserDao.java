package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.User;

@Repository("userDao")
@Mapper
public interface UserDao {
	public User selectUserById(Integer uid);
	public List<User> selectAllUser();
	public int addUser(User user);
	public int updateUser(User user);
	public int deleteUser(Integer uid);
}
