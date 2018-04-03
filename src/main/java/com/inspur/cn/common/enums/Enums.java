package com.inspur.cn.common.enums;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Enums {
    UP(0,"正常"),
    DOWN(1,"下架"),
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),
    WAIT(0,"等待"),
    SUCCESS(1,"支付成功"),
    PRODUTC_NOT_EXIST(101,"商品不存在"),
    STOCK(102,"库存不足"),
    ORDER_MASTER(103,"订单不存在"),
    ORDER_DETAIL(104,"订单详细信息不存在"),
    ORDER_STATUS_ERROR(105,"订单状态不正确"),
    ORDER_UPPDATE_ERROR(106,"订单更新失败"),
    ORDER_XQ(107,"订单中无商品详情"),
    ;

    private Integer code;
    private String msg;
}
