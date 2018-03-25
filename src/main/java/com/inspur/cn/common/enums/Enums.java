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
    success(1,"支付成功")
    ;

    private Integer code;
    private String msg;
}
