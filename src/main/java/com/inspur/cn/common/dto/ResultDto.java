package com.inspur.cn.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto<T> {

    private Integer code;

    private String msg;

    private T data;

}
