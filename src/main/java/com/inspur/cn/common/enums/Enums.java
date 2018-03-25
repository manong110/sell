package com.inspur.cn.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Enums {
    UP(0,"正常"),
    DOWN(1,"下架")
    ;

    private Integer code;
    private String msg;
}
