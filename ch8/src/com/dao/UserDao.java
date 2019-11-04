package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.User;

@Repository
@Mapper
public interface UserDao {
	public List<User> selectUserByIf(User user);
	public List<User> selectUserByChoose(User user);
	public List<User> selectUserByTrim(User user);
	public List<User> selectUserByWhere(User user);
	public int updateUserBySet(User user);
	public List<User> selectUserByForeach(List<Integer> listId);
	public List<User> selectUserByBind(User user);
}
