package com.xust.mall.service;

import com.xust.mall.common.CommonPage;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.Order;
import com.xust.mall.vo.MallUserVO;
import com.xust.mall.vo.OrderDetailVO;
import com.xust.mall.vo.OrderItemVO;
import com.xust.mall.vo.ShoppingCartItemVO;

import java.util.List;

public interface OrderService {
    CommonPage getOrdersPage(PageUtil pageUtil);

    String updateOrderInfo(Order order);

    String checkDone(Long[] ids);

    String checkOut(Long[] ids);

    String closeOrder(Long[] ids);

    String saveOrder(MallUserVO userVO, List<ShoppingCartItemVO> shoppingCartTemVOList);

    OrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId);

    Order getOrderByOrderNo(String orderNo);

    CommonPage getMyOrders(PageUtil pageUtil);

    String cancelOrder(String orderNo,Long userId);

    String confirmOrder(String orderNo,Long userId);

    String paySuccess(String orderNo,int payType);

    List<OrderItemVO> getOrderItems(Long id);

}
