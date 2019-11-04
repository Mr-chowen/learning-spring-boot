package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.pojo.Orders;
@Repository
@Mapper
public interface OrdersDao {
	
	public List<Orders> selectOrdersById(Integer uid);
	
	public List<Orders> selectallOrdersAndProducts();
	
}
