package com.inspur.cn.service.impl;

import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.repo.OrderMaster;
import com.inspur.cn.repository.OrderDetailRepository;
import com.inspur.cn.repository.OrderMasterRepository;
import com.inspur.cn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    @Override
    public OrderDto findOrder(String orderId) {
        return null;
    }

    /**
     * 查询订单列表
     * @param openid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderMaster> findByOpenid(String openid, Pageable pageable) {
        return null;
    }

    /**
     * 取消订单
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    /**
     * 完结订单
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    /**
     * 支付订单
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
