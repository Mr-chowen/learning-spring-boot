package com.xust.mall.service.impl;

import com.xust.mall.common.CommonPage;
import com.xust.mall.common.MallException;
import com.xust.mall.common.enums.OrderStatusEnum;
import com.xust.mall.common.enums.PayStatusEnum;
import com.xust.mall.common.enums.PayTypeEnum;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.BeanUtil;
import com.xust.mall.common.utils.NumberUtil;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.mapper.GoodsMapper;
import com.xust.mall.mapper.OrderItemMapper;
import com.xust.mall.mapper.OrderMapper;
import com.xust.mall.mapper.ShoppingCartItemMapper;
import com.xust.mall.model.Goods;
import com.xust.mall.model.Order;
import com.xust.mall.model.OrderItem;
import com.xust.mall.model.StockNumDTO;
import com.xust.mall.service.OrderService;
import com.xust.mall.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ShoppingCartItemMapper shoppingCartItemMapper;

    @Override
    public CommonPage getOrdersPage(PageUtil pageUtil) {
        List<Order> orders = orderMapper.selectOrders(pageUtil);
        int total = orderMapper.getTotalOrders(pageUtil);
        CommonPage page = new CommonPage(orders,total,pageUtil.getPage(),pageUtil.getLimit());
        return page;
    }

    /**
     * 更新订单信息
     * @param order
     * @return
     */
    @Override
    @Transactional
    public String updateOrderInfo(Order order) {
        Order temp = orderMapper.selectByPrimaryKey(order.getOrderId());
        //判断订单状态
        if(temp != null && temp.getOrderStatus() >= 0 && temp.getOrderStatus() < 3){
            temp.setTotalPrice(order.getTotalPrice());
            temp.setUserAddress(order.getUserAddress());
            temp.setUpdateTime(new Date());
            if(orderMapper.updateByPrimaryKeySelective(temp)>0){
                return ServiceResultEnum.SUCCESS.getMessage();
            }
            return ServiceResultEnum.DB_ERROR.getMessage();
        }
        return ServiceResultEnum.DATA_NOT_EXIST.getMessage();
    }

    /**
     * 配货
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public String checkDone(Long[] ids) {
        //查询所有订单判断订单状态 修改订单的状态和更新信息
        List<Order> orders = orderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if(!CollectionUtils.isEmpty(orders)){
            for (Order order : orders) {
                if(order.getIsDeleted() == 1){
                    errorOrderNos += order.getOrderNo() + " ";
                    continue;
                }
                if(order.getOrderStatus() != 1){
                    errorOrderNos += order.getOrderNo() + " ";
                }
            }
            if(StringUtils.isEmpty(errorOrderNos)){
                //订单状态正常 可以执行完成配货同时修改订单状态和更新时间
                if(orderMapper.checkDone(Arrays.asList(ids)) > 0){
                    return ServiceResultEnum.SUCCESS.getMessage();
                } else {
                    return ServiceResultEnum.DB_ERROR.getMessage();
                }
            } else {
                //订单状态异常
                if(errorOrderNos.length() > 0 && errorOrderNos.length() < 10){
                    return errorOrderNos + "订单未支付，无法出库";
                } else {
                    return "请检查订单信息，存在未支付订单，无法配货";
                }
            }
        }
        return ServiceResultEnum.DATA_NOT_EXIST.getMessage();
    }

    /**
     * 出库
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public String checkOut(Long[] ids) {
        List<Order> orders = orderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorderNos = "";
        if(!CollectionUtils.isEmpty(orders)){
            for (Order order: orders) {
                if(order.getIsDeleted() == 1){
                    errorderNos += order.getOrderNo() + " ";
                    continue;
                }
                if(order.getOrderStatus() != 1 && order.getOrderStatus() != 2){
                    errorderNos += order.getOrderNo() + " ";
                }
            }
            if(StringUtils.isEmpty(errorderNos)){
                if(orderMapper.checkOut(Arrays.asList(ids)) > 0){
                    return ServiceResultEnum.SUCCESS.getMessage();
                } else {
                    return ServiceResultEnum.DB_ERROR.getMessage();
                }
            } else {
                if(errorderNos.length() > 0 && errorderNos.length() < 100){
                    return errorderNos + "请检查订单状态";
                } else {
                    return "请检查订单信息，存在未支付/未配货订单，无法出库";
                }
            }
        }
        return ServiceResultEnum.DATA_NOT_EXIST.getMessage();
    }

    /**
     * 关闭订单
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public String closeOrder(Long[] ids) {
        List<Order> orders = orderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";

        if(!CollectionUtils.isEmpty(orders)){
            for (Order order : orders) {
                //isDelete = 1 订单已关闭
                if(order.getIsDeleted() == 1){
                    errorOrderNos += order.getOrderNo() + " ";
                    continue;
                }
                //已关闭或已完成订单
                if(order.getOrderStatus() == 4 || order.getOrderStatus() < 0){
                    errorOrderNos += order.getOrderNo() + " ";
                }
            }
            if(StringUtils.isEmpty(errorOrderNos)){
                if(orderMapper.closeOrder(Arrays.asList(ids), OrderStatusEnum.ORDER_CLOSED_BY_JUDGE.getOrderStatus()) > 0){
                    return ServiceResultEnum.SUCCESS.getMessage();
                } else {
                    return ServiceResultEnum.DB_ERROR.getMessage();
                }
            } else {
                if(errorOrderNos.length() > 0 && errorOrderNos.length() < 100){
                    return errorOrderNos + "订单状态异常";
                } else {
                    return "请检查订单状态";
                }
            }

        }
        return ServiceResultEnum.DATA_NOT_EXIST.getMessage();
    }

    /**
     * 保存
     * @param userVO
     * @param shoppingCartTemVOList
     * @return
     */
    @Override
    public String saveOrder(MallUserVO userVO, List<ShoppingCartItemVO> shoppingCartTemVOList) {
        List<Long> itemList = shoppingCartTemVOList.stream().map(ShoppingCartItemVO::getCartItemId).collect(Collectors.toList());
        List<Long> goodsIds = shoppingCartTemVOList.stream().map(ShoppingCartItemVO::getGoodsId).collect(Collectors.toList());
        List<Goods> goods = goodsMapper.selectByPrimaryKeys(goodsIds);
        Map<Long,Goods> goodsMap = goods.stream().collect(Collectors.toMap(Goods::getGoodsId, Function.identity(),(entity1,entity2)->entity1));
        //查出商品不存在购物车中的这条关联商品数据
        for(ShoppingCartItemVO shoppingCartItemVO : shoppingCartTemVOList){
            if(!goodsMap.containsKey(shoppingCartItemVO.getGoodsId())){
                MallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getMessage());
            }
            //存在大于库存情况
            if(shoppingCartItemVO.getGoodsCount() > goodsMap.get(shoppingCartItemVO.getGoodsId()).getStockNum()){
                MallException.fail(ServiceResultEnum.SHOPPING_ITEM_COUNT_ERROR.getMessage());
            }
        }
        //删除购物项
        if(!CollectionUtils.isEmpty(itemList) && !CollectionUtils.isEmpty(goodsIds) && !CollectionUtils.isEmpty(goods)){
            if(shoppingCartItemMapper.deleteBatch(itemList) > 0){
                List<StockNumDTO> stockNums = BeanUtil.copyList(shoppingCartTemVOList,StockNumDTO.class);
                int updateStockResult = goodsMapper.updateStockNum(stockNums);

                if(updateStockResult < 1){
                    MallException.fail(ServiceResultEnum.SHOPPING_ITEM_COUNT_ERROR.getMessage());
                }
                //订单号生成
                String orderNo = NumberUtil.genOrderNo();
                int priceTotal = 0;
                //保存订单
                Order order = new Order();
                order.setUserId(userVO.getUserId());
                order.setOrderNo(orderNo);
                order.setUserAddress(userVO.getAddress());
                //总价
                for(ShoppingCartItemVO shoppingCartItemVO : shoppingCartTemVOList){
                    priceTotal += shoppingCartItemVO.getGoodsCount()  * shoppingCartItemVO.getSellingPrice();
                }
                if(priceTotal < 1){
                    MallException.fail(ServiceResultEnum.ORDER_PRICE_ERROR.getMessage());
                }
                order.setTotalPrice(priceTotal);
                String extraInfo = "";
                order.setExtraInfo(extraInfo);
                //生成订单项并保存订单项记录
                if(orderMapper.insertSelective(order) > 0){
                    List<OrderItem> orderItems = new ArrayList<>();
                    for (ShoppingCartItemVO shopingCartItemVo : shoppingCartTemVOList) {
                        OrderItem orderItem = new OrderItem();
                        //使用BeanUtil工具类将newBeeMallShoppingCartItemVO中的属性复制到newBeeMallOrderItem对象中
                        BeanUtil.copyProperties(shopingCartItemVo,orderItem);
                        orderItem.setOrderId(order.getOrderId());
                        orderItems.add(orderItem);
                    }
                    //保存到数据库中
                    if(orderItemMapper.insertBatch(orderItems) > 0){
                        //保存成功后返回订单号
                        return orderNo;
                    }
                    MallException.fail(ServiceResultEnum.ORDER_PRICE_ERROR.getMessage());
                }
                MallException.fail(ServiceResultEnum.DB_ERROR.getMessage());
            }
            MallException.fail(ServiceResultEnum.DB_ERROR.getMessage());
        }
        MallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getMessage());
        return ServiceResultEnum.SHOPPING_ITEM_ERROR.getMessage();
    }

    /**
     * 订单详情
     * @param orderNo
     * @param userId
     * @return
     */
    @Override
    public OrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId) {
        Order  order = orderMapper.selectByOrderNo(orderNo);
        if(order != null){
            List<OrderItem> orderItems = orderItemMapper.selectByOrderId(order.getOrderId());
            if(!CollectionUtils.isEmpty(orderItems)){
                List<OrderItemVO> orderItemVOS = BeanUtil.copyList(orderItems,OrderItemVO.class);
                OrderDetailVO orderDetailVO = new OrderDetailVO();
                BeanUtil.copyProperties(order,orderDetailVO);
                orderDetailVO.setOrderStatusString(OrderStatusEnum.getOrderStatusEnumByStatus(orderDetailVO.getOrderStatus()).getName());
                orderDetailVO.setPayTypeString(PayTypeEnum.getPayTypeEnumByType(orderDetailVO.getPayType()).getName());
                orderDetailVO.setOrderItemVOS(orderItemVOS);
                return orderDetailVO;
            }
        }
        return null;
    }

    /**
     * 根据订单号获取订单信息
     * @param orderNo
     * @return
     */
    @Override
    public Order getOrderByOrderNo(String orderNo) {
        return orderMapper.selectByOrderNo(orderNo);
    }

    /**
     * 获取订单
     * @param pageUtil
     * @return
     */
    @Override
    public CommonPage getMyOrders(PageUtil pageUtil) {
        int total = orderMapper.getTotalOrders(pageUtil);
        List<Order>  orders = orderMapper.selectOrders(pageUtil);
        List<OrderListVO> orderListVOS = new ArrayList<>();
        if(total > 0){
            //数据转换
            orderListVOS = BeanUtil.copyList(orders,OrderListVO.class);

            for(OrderListVO orderListVO : orderListVOS){
                orderListVO.setOrderStatusString(OrderStatusEnum.getOrderStatusEnumByStatus(orderListVO.getOrderStatus()).getName());
            }
            List<Long> orderIds = orders.stream().map(Order::getOrderId).collect(Collectors.toList());
            if(!CollectionUtils.isEmpty(orderIds)){
                List<OrderItem> orderItems = orderItemMapper.selectByOrderIds(orderIds);
                Map<Long,List<OrderItem>> orderIdMap = orderItems.stream().collect(groupingBy(OrderItem::getOrderId));
                for (OrderListVO orderListVO : orderListVOS){
                    //封装每个订单列表对象的订单项数据
                    if(orderIdMap.containsKey(orderListVO.getOrderId())){
                       List<OrderItem> orderItemList = orderIdMap.get(orderListVO.getOrderId());
                       List<OrderItemVO> orderItemVOS = BeanUtil.copyList(orderItemList,OrderItemVO.class);
                       orderListVO.setOrderItemVOS(orderItemVOS);
                    }
                }
            }
        }
        CommonPage page = new CommonPage(orderListVOS,total,pageUtil.getPage(),pageUtil.getLimit());
        return page;
    }

    /**
     * 取消订单
     * @param orderNo
     * @param userId
     * @return
     */
    @Override
    public String cancelOrder(String orderNo, Long userId) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order != null){
            //验证是否是当前用户下的订单及订单状态判断
            if(orderMapper.closeOrder(Collections.singletonList(order.getOrderId()),OrderStatusEnum.ORDER_CLOSED_BY_MALLUSER.getOrderStatus()) >0){
                return ServiceResultEnum.SHOPPING_ITEM_ERROR.getMessage();
            } else {
                return ServiceResultEnum.DB_ERROR.getMessage();
            }
        }
        return ServiceResultEnum.ORDER_NOT_EXIST_ERROR.getMessage();
    }

    /**
     * 确认订单(订单交易完成)
     * @param orderNo
     * @param userId
     * @return
     */
    @Override
    public String confirmOrder(String orderNo, Long userId) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order != null){
            order.setOrderStatus((byte)OrderStatusEnum.ORDER_SUCCESS.getOrderStatus());
            order.setUpdateTime(new Date());
            if(orderMapper.updateByPrimaryKeySelective(order) > 0){
                return ServiceResultEnum.SUCCESS.getMessage();
            } else {
                return ServiceResultEnum.DB_ERROR.getMessage();
            }
        }
        return ServiceResultEnum.ORDER_NOT_EXIST_ERROR.getMessage();
    }

    /**
     * 订单支付
     * @param orderNo
     * @param payType
     * @return
     */
    @Override
    public String paySuccess(String orderNo, int payType) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order != null){
            //订单状态判断,非支付状态不进行修改
            order.setOrderStatus((byte)OrderStatusEnum.OREDER_PAID.getOrderStatus());
            order.setPayType((byte)payType);
            order.setPayStatus((byte) PayStatusEnum.PAY_SUCCESS.getPayStatus());
            order.setPayTime(new Date());
            order.setUpdateTime(new Date());
            if(orderMapper.updateByPrimaryKeySelective(order) > 0){
                return ServiceResultEnum.SUCCESS.getMessage();
            } else {
                return ServiceResultEnum.DB_ERROR.getMessage();
            }
        }
        return ServiceResultEnum.ORDER_NOT_EXIST_ERROR.getMessage();
    }

    /**
     * 获取订单项数据
     * @param id
     * @return
     */
    @Override
    public List<OrderItemVO> getOrderItems(Long id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        if(order != null){
            List<OrderItem> orderItems = orderItemMapper.selectByOrderId(order.getOrderId());
            if(!CollectionUtils.isEmpty(orderItems)){
                List<OrderItemVO> orderItemVOS = BeanUtil.copyList(orderItems,OrderItemVO.class);
                return orderItemVOS;
            }
        }
        return null;
    }
}
