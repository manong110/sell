package com.inspur.cn.service;

import com.inspur.cn.common.dto.CartDto;
import com.inspur.cn.repo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductInfoService {

    ProductInfo save(ProductInfo productInfo);

    Optional<ProductInfo> findById(String id);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAllList(Pageable pageable);

    //加库存
    void increaseStock(List<CartDto> cartDtoList);

    //减库存
    void decreaseStork(List<CartDto> cartDtoList);
}
