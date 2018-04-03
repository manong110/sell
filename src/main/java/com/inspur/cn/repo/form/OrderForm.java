package com.inspur.cn.repo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderForm {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @NotBlank(message = "地址不能为空")
    private String address;

    @NotBlank(message = "微信id不能为空")
    private String openid;

    @NotBlank(message = "购物车不能为空")
    private String items;
}
