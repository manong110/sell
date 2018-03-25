package com.inspur.cn.controller;

import com.inspur.cn.common.dto.ProductDto;
import com.inspur.cn.common.dto.ProductInfoDto;
import com.inspur.cn.common.dto.ProductInfoUtil;
import com.inspur.cn.common.dto.ResultDto;
import com.inspur.cn.repo.Category;
import com.inspur.cn.repo.ProductInfo;
import com.inspur.cn.service.CategoryService;
import com.inspur.cn.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultDto getList(){

        //1 查询所有上架商品
        List<ProductInfo> list = productInfoService.findUpAll();

        //2 查询类目
        List<Integer> typeList = list.stream()
                .map(e -> e.getType())
                .collect(Collectors.toList());

        List<Category> byTypeIn = categoryService.findByTypeIn(typeList);
        ProductDto productDto =null;
        List<ProductInfoDto> productInfoList = new ArrayList<>();
        //数据拼装
        for (Category category:byTypeIn) {
            productDto = ProductDto.builder().categoryName(category.getName()).type(category.getType()).build();
            for (ProductInfo productInfo : list){
                if(category.getType().equals(productInfo.getType())){
                    ProductInfoDto productInfoDto = new ProductInfoDto();
                    BeanUtils.copyProperties(productInfo,productInfoDto);
                    productInfoList.add(productInfoDto);
                }
            }
            productDto.setFoods(productInfoList);
        }
        return ProductInfoUtil.success(productDto);
    }
}
