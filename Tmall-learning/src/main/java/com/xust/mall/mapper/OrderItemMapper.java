package com.xust.mall.mapper;

import com.xust.mall.model.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderItemMapper {
    int deleteByPrimaryKey(Long orderItemId);

    int insert(OrderItem orderItem);

    int insertSelective(OrderItem orderItem);

    int insertBatch(@Param("orderItems") List<OrderItem> orderItems);

    OrderItem selectByPrimaryKey(Long orderItemId);

    List<OrderItem> selectByOrderId(Long orderId);

    List<OrderItem> selectByOrderIds(@Param("orderIds") List<Long> orderIds);

    int updateByPrimaryKey(OrderItem orderItem);

    int updateByPrimaryKeySelective(OrderItem orderItem);
}
