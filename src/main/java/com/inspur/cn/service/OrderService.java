package com.inspur.cn.service;

import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.repo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

    //创建订单
    OrderDto createOrder(OrderDto orderDto);

    //查询单个订单
    OrderDto  findOrder(String orderId);

    //查询订单列表
    Page<OrderDto> findByOpenid(String openid, Pageable pageable);

    //取消订单 Integer orderStatus
    OrderDto cancel(OrderDto orderDto);

    //完结订单
    OrderDto finish(OrderDto orderDto);

    //支付订单
    OrderDto paid(OrderDto orderDto);
}
