package com.inspur.cn.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品包含类目
 */
@Data
public class ProductInfoDto {

    private String id;

    //商品价格
    private BigDecimal price;

    @JsonProperty("name")
    //商品名称
    private String pname;

    @JsonProperty("description")
    private String productDesc;

    @JsonProperty("icon")
    private String picture;

}
