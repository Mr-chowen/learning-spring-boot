package com.xust.service.impl;

import com.xust.common.api.CommonResult;
import com.xust.component.CancelOrderSender;
import com.xust.dto.OrderParam;
import com.xust.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService{
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        LOGGER.info("process generateOrder");
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null,"下单成功");
    }

    private void sendDelayMessageCancelOrder(long orderId) {
        //获取订单超时时间，假设为60分钟
        long delatTimes = 30 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId,delatTimes);
    }

    @Override
    public void cancelOrder(Long orderId) {

        LOGGER.info("process cancelOrder orderId:{}",orderId);
    }
}
