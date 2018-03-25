package com.inspur.cn.repository;

import com.inspur.cn.repo.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final static String OPENID = "1234";

    @Test
    public void saveTest(){
        OrderMaster master = OrderMaster.builder().id("2").name("小方").phone("18239947002")
                .address("四川省泸州市江阳区").openid(OPENID)
                .orderAmount(new BigDecimal(30)).orderStatus(0)
                .payStatus(0).build();
        OrderMaster save = orderMasterRepository.save(master);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByOpenidTest(){
        PageRequest request = PageRequest.of(0,1);
        Page<OrderMaster> result = orderMasterRepository.findByOpenid(OPENID, request);
        Assert.assertNotEquals(0,result.getTotalElements());
    }

}