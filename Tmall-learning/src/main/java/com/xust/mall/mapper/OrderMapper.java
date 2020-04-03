package com.xust.mall.mapper;

import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order order);

    int insertSelective(Order order);

    Order selectByPrimaryKey(Long id);

    Order selectByOrderNo(String orderNo);

    int updateByPrimaryKey(Order order);

    int updateByPrimaryKeySelective(Order order);

    List<Order> selectOrders(PageUtil pageUtil);

    int getTotalOrders(PageUtil pageUtil);

    List<Order> selectByPrimaryKeys(@Param("orderIds") List<Long> orderIds);

    int checkOut(@Param("orderIds") List<Long> orderIds);

    int closeOrder(@Param("orderIds") List<Long> orderIds,@Param("orderStatus") int orderStatus);

    int checkDone(@Param("orderIds") List<Long> asList);
}
