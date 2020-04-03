package com.xust.mall.controller.admin;

import com.xust.mall.common.CommonResult;
import com.xust.mall.common.ResultCode;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.Order;
import com.xust.mall.service.OrderService;
import com.xust.mall.vo.OrderItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String orderPage(HttpServletRequest request){
        request.setAttribute("path","orders");
        return "admin/mall_order";
    }

    /**
     * 列表
     * @param params
     * @return
     */
    @GetMapping("/orders/list")
    @ResponseBody
    public CommonResult list(@RequestParam Map<String,Object> params){
        if(StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultCode.FailResult("请求参数异常");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultCode.SuccessResult(orderService.getOrdersPage(pageUtil));
    }

    /**
     * 修改
     * @param order
     * @return
     */
    @PostMapping("/orders/update")
    @ResponseBody
    public CommonResult update(@RequestBody Order order){
        if(Objects.isNull(order.getTotalPrice())
            || Objects.isNull(order.getOrderId())
            || order.getOrderId() < 1
            || order.getTotalPrice() < 1
            || StringUtils.isEmpty(order.getUserAddress())){
            return ResultCode.FailResult("请求参数异常");
        }
        String result = orderService.updateOrderInfo(order);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else {
            return ResultCode.FailResult(result);
        }
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/orders/info/{id}")
    @ResponseBody
    public CommonResult info(@PathVariable("id") Long id){
        List<OrderItemVO> orderItemVOS = orderService.getOrderItems(id);
        if(!CollectionUtils.isEmpty(orderItemVOS)){
            return ResultCode.SuccessResult(orderItemVOS);
        }
        return ResultCode.FailResult(ServiceResultEnum.DATA_NOT_EXIST.getMessage());
    }

    /**
     * 配货
     * @return
     */
    @PostMapping("/orders/checkDone")
    @ResponseBody
    public CommonResult checkDone(@RequestBody Long[] ids){
        if(ids.length < 1){
            return ResultCode.FailResult("请求参数异常");
        }

        String result = orderService.checkDone(ids);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else {
            return ResultCode.FailResult(result);
        }
    }

    /**
     * 出库
     * @param ids
     * @return
     */
    @PostMapping("/orders/checkOut")
    @ResponseBody
    public CommonResult checkOut(@RequestBody Long[] ids){
        if(ids.length < 1){
            return ResultCode.FailResult("请求参数异常");
        }
        String result = orderService.checkOut(ids);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else {
            return ResultCode.FailResult(result);
        }
    }

    /**
     * 关闭订单
     * @param ids
     * @return
     */
    @PostMapping("/orders/close")
    @ResponseBody
    public CommonResult closeOrder(@RequestBody Long[] ids){
        if(ids.length < 1){
            return ResultCode.FailResult("请求参数异常");
        }

        String result = orderService.closeOrder(ids);

        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else {
            return  ResultCode.FailResult(result);
        }
    }
}
