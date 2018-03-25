package com.inspur.cn.common.dto;

import java.util.Arrays;

public class ProductInfoUtil{

    public static ResultDto success(ProductDto productDto){
        return ResultUtil.success(Arrays.asList(productDto));
    }
}
