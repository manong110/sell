package com.inspur.cn.repo.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class ParamForm {

    @NotBlank(message = "微信号不能为空")
    private String openid;

    @NotBlank(message = "订单号不能为空")
    private String orderId;
}
