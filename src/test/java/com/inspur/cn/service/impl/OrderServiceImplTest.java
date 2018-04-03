package com.inspur.cn.service.impl;

import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.common.dto.ResultDto;
import com.inspur.cn.repo.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String OPENID = "1234";

    private final String ORDER_ID = "1522292169580988821";

    private final String PRODUCT_ID = "1";
    @Test
    public void createOrder() throws Exception {
        OrderDto orderDto = OrderDto.builder().address("达州").name("小燕").phone("12345678923")
                .openid(OPENID).build();
        OrderDetail build = OrderDetail.builder()
                .orderId(ORDER_ID).productId(PRODUCT_ID)
                .total(3).build();

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(build);

        orderDto.setOrderDetailList(orderDetails);
        OrderDto result = orderService.createOrder(orderDto);
        log.info("result->{}",result.toString());
    }

    @Test
    public void findOrder() throws Exception {
        OrderDto result = orderService.findOrder(ORDER_ID);
        log.info("[查询单个订单] result-{}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenid() throws Exception {
        PageRequest request = PageRequest.of(0,2);
        Page<OrderDto> orderDtoPage = orderService.findByOpenid(OPENID, request);
        Assert.assertNotNull(orderDtoPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDto order = orderService.findOrder(ORDER_ID);
        OrderDto cancel = orderService.cancel(order);
        Assert.assertEquals(new Integer(2),cancel.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {

    }

    @Test
    public void paid() throws Exception {
    }

}