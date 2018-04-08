package com.inspur.cn.service.impl;

import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.common.enums.Enums;
import com.inspur.cn.common.exceptions.SellException;
import com.inspur.cn.common.util.MathUtil;
import com.inspur.cn.common.util.jsonUtil;
import com.inspur.cn.service.OrderService;
import com.inspur.cn.service.PayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService{

    private static final String ORDER_NAME ="微信点餐订单";

    @Autowired
    private BestPayService bestPayService;

    @Autowired
    private OrderService orderService;

    /**
     * 发起支付
     * @param orderDto
     * @return
     */
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

    /**
     * 支付订单
     * @param notifyData
     * @return
     */
    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付状态
        //3.支付金额
        //4.支付人（下单人==支付人）
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("[微信支付] 异步通知,response->{}",jsonUtil.tojson(response));
        //修改订单的支付状态
        OrderDto order = orderService.findOrder(response.getOrderId());
        if(order==null){
            log.error("[微信支付] 异步通知 订单查询。orderId->{}",response.getOrderId());
            throw new SellException(Enums.ORDER_DETAIL);
        }
        if(!MathUtil.equals(order.getOrderAmount().doubleValue(), response.getOrderAmount())){
            log.error("[微信支付] 异步通知 订单金额不一致。订单金额->{}," +
                    "微信异步通知金额->{}",response.getOrderId(),response.getOrderAmount());
            throw new SellException(Enums.ORDER_AMOUNT_ERROR);
        };

        OrderDto paid = orderService.paid(order);

        return response;
    }

    /**
     * 退款
     * @param orderDto
     */
    @Override
    public RefundResponse refund(OrderDto orderDto) {
        RefundRequest request = new RefundRequest();
        request.setOrderId(orderDto.getId());
        request.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.error("[微信退款] request->{}",jsonUtil.tojson(request));
        RefundResponse response = bestPayService.refund(request);
        log.error("[微信退款] response->{}",jsonUtil.tojson(request));
        return response;
    }
}
