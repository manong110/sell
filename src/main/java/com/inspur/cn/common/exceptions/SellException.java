package com.inspur.cn.common.exceptions;

import com.inspur.cn.common.enums.Enums;
import lombok.Data;

@Data
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(Enums enums) {
        super(enums.getMsg());
        this.code=enums.getCode();
    }
}
