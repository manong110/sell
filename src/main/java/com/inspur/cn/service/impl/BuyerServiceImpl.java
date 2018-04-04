package com.inspur.cn.service.impl;

import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.common.enums.Enums;
import com.inspur.cn.common.exceptions.SellException;
import com.inspur.cn.repo.form.ParamForm;
import com.inspur.cn.service.BuyerService;
import com.inspur.cn.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(@Valid ParamForm paramForm, BindingResult result) {
        return checkOrderDto(paramForm, result);
    }

    @Override
    public OrderDto cancelOrder(@Valid ParamForm paramForm, BindingResult result) {
        return orderService.cancel(findOrderOne(paramForm, result));
    }

    private OrderDto checkOrderDto(@Valid ParamForm paramForm, BindingResult result){
        if(result.hasErrors()){
            log.error("[查询订单]参数不能为空");
            throw new SellException(Enums.PARAM_ERROR.getCode(),result.getFieldError().getDefaultMessage());
        }
        OrderDto order = orderService.findOrder(paramForm.getOrderId());
        if(order==null){
            log.error("[查询订单]订单不存在 orderid->{}",paramForm.getOrderId());
            throw new SellException(Enums.ORDER_MASTER);
        }
        boolean b = StringUtils.equals(order.getOpenid(), paramForm.getOpenid());
        if(!b){
            log.error("[查询订单]订单openid不一致orderId->{},order->{}",paramForm.getOrderId(),order);
            throw new SellException(Enums.ORDER_OPENID_ERROR);
        }
        return order;
    }
}
