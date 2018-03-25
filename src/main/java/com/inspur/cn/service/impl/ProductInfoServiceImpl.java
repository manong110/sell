package com.inspur.cn.service.impl;

import com.inspur.cn.common.enums.Enums;
import com.inspur.cn.repo.ProductInfo;
import com.inspur.cn.repository.ProductInfoRepository;
import com.inspur.cn.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     * 添加商品信息
     * @param productInfo
     * @return
     */
    @Override
    public ProductInfo save(ProductInfo productInfo) {
//        ProductInfo info =
//                ProductInfo.builder().id(productInfo.getId()).pname(productInfo.getPname())
//                        .price(productInfo.getPrice()).stock(productInfo.getStock()).type(productInfo.getType())
//                        .picture(productInfo.getPicture()).productDesc(productInfo.getProductDesc())
//                        .productStatus(productInfo.getProductStatus()).build();
        return productInfoRepository.save(productInfo);
    }

    /**
     * 查询商品信息
     * @param id
     * @return
     */
    @Override
    public ProductInfo findOne(String id) {
        return productInfoRepository.getOne(id);
    }

    /**
     *查询所有上架信息
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(Enums.UP.getCode());
    }

    /**
     * 通过分页查询所有商品
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }
}
