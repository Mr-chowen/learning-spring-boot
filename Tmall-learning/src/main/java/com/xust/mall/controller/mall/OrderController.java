package com.xust.mall.controller.mall;

import com.xust.mall.common.CommonResult;
import com.xust.mall.common.MallException;
import com.xust.mall.common.ResultCode;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.ConstantsUtil;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.Order;
import com.xust.mall.service.OrderService;
import com.xust.mall.service.ShoppingCartService;
import com.xust.mall.vo.MallUserVO;
import com.xust.mall.vo.OrderDetailVO;
import com.xust.mall.vo.ShoppingCartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders/{orderNo}")
    public String orderDetailPage(HttpServletRequest request, @PathVariable("orderNo") String orderNo, HttpSession session){
        MallUserVO mallUserVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        OrderDetailVO orderDetailVO = orderService.getOrderDetailByOrderNo(orderNo,mallUserVO.getUserId());
        if(orderDetailVO == null){
            return "error/error_5xx";
        }
        request.setAttribute("orderDetailVO",orderDetailVO);
        return "mall/order-detail";
    }

    @GetMapping("/orders")
    public String orderListPage(@RequestParam Map<String,Object> params,HttpServletRequest request,HttpSession session){
        MallUserVO mallUserVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        params.put("userId",mallUserVO.getUserId());
        if(StringUtils.isEmpty(params.get("page"))){
            params.put("page",1);
        }
        params.put("limit",ConstantsUtil.ORDER_SEARCH_PAGE_LIMIT);

        PageUtil pageUtil = new PageUtil(params);
        request.setAttribute("orderPageResult",orderService.getMyOrders(pageUtil));
        request.setAttribute("path","orders");
        return "mall/my-orders";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session){
        MallUserVO mallUserVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        List<ShoppingCartItemVO> shoppingCartItemVOS = shoppingCartService.getMyShoppingCartItems(mallUserVO.getUserId());
        //收货地址为空
        if(StringUtils.isEmpty(mallUserVO.getAddress().trim())){
            MallException.fail(ServiceResultEnum.NULL_ADDRESS_ERROR.getMessage());
        }
        //购物车为空
        if(CollectionUtils.isEmpty(shoppingCartItemVOS)){
            MallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getMessage());
        }
        String result = orderService.saveOrder(mallUserVO,shoppingCartItemVOS);
        return "redirect:/orders/"+result;
    }

    @PutMapping("/orders/{orderNo}/cancel")
    @ResponseBody
    public CommonResult cancelOrder(@PathVariable("orderNo") String orderNo,HttpSession session){
        MallUserVO mallUserVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        String result = orderService.cancelOrder(orderNo,mallUserVO.getUserId());
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else {
            return ResultCode.FailResult(result);
        }
    }

    @PutMapping("/orders/{orderNo}/finish")
    @ResponseBody
    public CommonResult finishOrder(@PathVariable("orderNo") String orderNo,HttpSession session){
        MallUserVO mallUserVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        String result = orderService.confirmOrder(orderNo,mallUserVO.getUserId());
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else {
            return  ResultCode.FailResult(result);
        }
    }

    @GetMapping("/selectPayType")
    public String selectPayType(HttpServletRequest request,@RequestParam("orderNo") String orderNo,HttpSession session){
        MallUserVO mallUserVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        Order order = orderService.getOrderByOrderNo(orderNo);
        request.setAttribute("orderNo",orderNo);
        request.setAttribute("totalPrice",order.getTotalPrice());
        return "mall/pay-select";
    }

    @GetMapping("/payPage")
    public String payOrder(HttpServletRequest request,@RequestParam("orderNo") String orderNo,HttpSession session,@RequestParam("payType") int payType){
        MallUserVO mallUserVO = (MallUserVO) session.getAttribute(ConstantsUtil.MALL_USER_SESSION_KEY);
        Order order = orderService.getOrderByOrderNo(orderNo);
        request.setAttribute("orderNo",orderNo);
        request.setAttribute("totalPrice",order.getTotalPrice());
        if(payType == 1){
            return "mall/alipay";
        } else {
            return "mall/wxpay";
        }
    }

    @GetMapping("/paySuccess")
    @ResponseBody
    public CommonResult paySuccess(@RequestParam("orderNo") String orderNo,@RequestParam("payType") int payType){
        String result = orderService.paySuccess(orderNo,payType);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else {
            return ResultCode.FailResult(result);
        }
    }
}
