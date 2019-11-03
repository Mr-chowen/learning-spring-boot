package com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.pojo.Idcard;
@Repository
@Mapper
public interface IdcardDao {
	public int addIdcard(Idcard idcard);
	public Idcard selectCodeById(Integer id);
}
