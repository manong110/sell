package com.inspur.cn.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductDto {

    @JsonProperty("name")
    private String categoryName;

    private Integer type;

    private List<ProductInfoDto> foods;
}
