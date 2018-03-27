package com.inspur.cn.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class CartDto {

    private String orderId; //订单id

    private Integer total;//商品数量

}
