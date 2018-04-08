package com.inspur.cn.service.impl;

import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.common.util.jsonUtil;
import com.inspur.cn.service.OrderService;
import com.inspur.cn.service.PayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService{

    private static final String ORDER_NAME ="微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDto orderDto) {
        PayRequest request = new PayRequest();
        request.setOpenid(orderDto.getOpenid());
        request.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        request.setOrderId(orderDto.getId());
        request.setOrderName(PayServiceImpl.ORDER_NAME);
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信支付] 发起支付 requset->{}", jsonUtil.tojson(request));
        PayResponse response = bestPayService.pay(request);
        log.info("[微信支付] 发起支付 response->{}", jsonUtil.tojson(response));
        return response;
    }

    @Override
    public PayResponse notify(String notifyData) {
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("[微信支付] 异步通知,response->{}",jsonUtil.tojson(response));
        //修改订单的支付状态
        OrderDto order = orderService.findOrder(response.getOrderId());
        OrderDto paid = orderService.paid(order);
        return response;
    }
}
