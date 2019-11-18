package com.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.Auser;
@Repository("adminLoginDao")
@Mapper
public interface AdminLoginDao {
	public List<Auser> login(Auser auser);
}
