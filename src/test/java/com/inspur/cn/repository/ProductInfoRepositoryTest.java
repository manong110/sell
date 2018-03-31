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
import java.util.Optional;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest(){
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

    @Test
    public void find() throws Exception{
        Optional<ProductInfo> id = productInfoRepository.findById("1");
        Assert.assertNotNull(id);
    }
}