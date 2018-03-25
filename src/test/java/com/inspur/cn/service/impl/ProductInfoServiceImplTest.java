package com.inspur.cn.service.impl;

import com.inspur.cn.repo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void save() throws Exception {
        ProductInfo productInfo=
                ProductInfo.builder().id("3").pname("兰州拉面")
                        .price(new BigDecimal(10))
                        .productDesc("很好吃").type(1).productStatus(0).stock(100)
                        .picture("http://****.jpg").build();
        ProductInfo info = productInfoService.save(productInfo);
        Assert.assertNotNull(info);
    }

    @Test
    public void findOne() throws Exception {
        ProductInfo one = productInfoService.findOne("1");
        Assert.assertNotNull(one);
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> upAll = productInfoService.findUpAll();
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    public void findAll() throws Exception {
        //productInfoService.findAll();
    }

}