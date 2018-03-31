package com.inspur.cn.service.impl;

import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.common.dto.ResultDto;
import com.inspur.cn.repo.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    @Test
    public void createOrder() throws Exception {
        OrderDto orderDto = OrderDto.builder().address("成都").name("小米mix2S").phone("123456789")
                .openid(OPENID).build();
        OrderDetail build = OrderDetail.builder().orderId("1").total(3).build();
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(build);

        orderDto.setOrderDetailList(orderDetails);
        OrderDto result = orderService.createOrder(orderDto);
        log.info("result->{}",result.toString());
    }

    @Test
    public void findOrder() throws Exception {
    }

    @Test
    public void findByOpenid() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}