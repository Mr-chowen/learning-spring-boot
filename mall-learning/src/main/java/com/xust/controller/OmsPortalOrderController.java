package com.xust.controller;

import com.xust.dto.OrderParam;
import com.xust.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Api(tags = "OmsPortalOrderController")
@Controller
@RequestMapping("/order")
public class OmsPortalOrderController {
    @Autowired
    private OmsPortalOrderService omsPortalOrderService;
    @ApiOperation("根据购物车生成订单信息")
    @ResponseBody
    @RequestMapping(value = "/generateOrder",method = RequestMethod.POST)
    public Object generateOrder(@RequestBody OrderParam orderParam){
        return omsPortalOrderService.generateOrder(orderParam);
    }
}
