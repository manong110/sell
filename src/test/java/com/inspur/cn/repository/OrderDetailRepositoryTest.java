package com.inspur.cn.repository;

import com.inspur.cn.repo.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {

    @Autowired OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest(){
        OrderDetail build = OrderDetail.builder().id("1").name("烩面")
                .picture("http://*******.jpg").price(new BigDecimal(10))
                .total(1).orderId("1").build();
        OrderDetail save = orderDetailRepository.save(build);
        Assert.assertNotNull(save);
    }


    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> detailList = orderDetailRepository.findByOrderId("1");
        Assert.assertNotEquals(0,detailList.size());
    }

}