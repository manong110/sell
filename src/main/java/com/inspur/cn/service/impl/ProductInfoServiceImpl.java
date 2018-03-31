package com.inspur.cn.service.impl;

import com.inspur.cn.common.dto.CartDto;
import com.inspur.cn.common.enums.Enums;
import com.inspur.cn.common.exceptions.SellException;
import com.inspur.cn.repo.ProductInfo;
import com.inspur.cn.repository.ProductInfoRepository;
import com.inspur.cn.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class ProductInfoServiceImpl  implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     * 添加商品信息
     * @param productInfo
     * @return
     */
    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    /**
     * 查询商品信息
     * @param id
     * @return
     */
    @Override
    public Optional<ProductInfo> findById(String id) {
        return productInfoRepository.findById(id);
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
    public Page<ProductInfo> findAllList(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    /**
     * 加库存
     * @param cartDtoList
     */
    @Override
    public void increaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto :  cartDtoList) {
            ProductInfo info = productInfoRepository.getOne(cartDto.getOrderId());
            if( info == null ){
                throw  new SellException(Enums.PRODUTC_NOT_EXIST);
            }
            info.setStock(info.getStock() + cartDto.getTotal());
            productInfoRepository.save(info);
        }
    }

    /**
     * 扣库存
     * @param cartDtoList
     */
    @Override
    public void decreaseStork(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            Optional<ProductInfo> optional = productInfoRepository.findById(cartDto.getOrderId());
            ProductInfo info = optional.get();
            if (info == null) {
                throw new SellException(Enums.PRODUTC_NOT_EXIST);
            }
            Integer result = info.getStock() - cartDto.getTotal();
            if (result < 0) {
                throw new SellException(Enums.STOCK);
            }
            info.setStock(result);
            productInfoRepository.save(info);
        }
    }

}
