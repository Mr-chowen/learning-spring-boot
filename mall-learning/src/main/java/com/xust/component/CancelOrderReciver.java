package com.xust.component;


import com.xust.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CancelOrderReciver {
    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderReciver.class);

    @Autowired
    private OmsPortalOrderService portalOrderService;

    public void handle(Long orderId){
        LOGGER.info("receive delay message orderId:{}",orderId);
        portalOrderService.cancelOrder(orderId);
    }
}
