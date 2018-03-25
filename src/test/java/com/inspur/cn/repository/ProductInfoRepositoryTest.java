package com.inspur.cn.repository;

import com.inspur.cn.repo.ProductInfo;
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
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest(){
//        productInfo.setId("1");
//        productInfo.setPname("胡辣汤");
//        productInfo.setPrice(new BigDecimal(3.2));
//        productInfo.setProductDesc("逍遥镇胡辣汤");
//        productInfo.setType(1);
//        productInfo.setProductStatus(0);
//        productInfo.setStock(100);
//        productInfo.setPicture("http://****.jpg");
        ProductInfo productInfo=
                ProductInfo.builder().id("2").pname("烩面")
                        .price(new BigDecimal(10))
                        .productDesc("很好吃").type(1).productStatus(0).stock(100)
                        .picture("http://****.jpg").build();
        ProductInfo save = productInfoRepository.save(productInfo);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByStatus() throws Exception {
        List<ProductInfo> status = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,status.size());
    }

}