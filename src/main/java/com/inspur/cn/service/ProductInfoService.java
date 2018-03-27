package com.inspur.cn.service;

import com.inspur.cn.common.dto.CartDto;
import com.inspur.cn.repo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo save(ProductInfo productInfo);

    ProductInfo findOne(String id);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    //加库存
    void increaseStock(List<CartDto> cartDtoList);

    //减库存
    void decreaseStork(List<CartDto> cartDtoList);
}
