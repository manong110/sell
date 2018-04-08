package com.inspur.cn.repo.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class OrderForm {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "手机号不能为空")
    @Length(max = 11, min = 11,message = "手机号格式不对")
//    @Pattern(regexp = "\\d{11}", message = "{actno.format.error}")
    private String phone;

    @NotBlank(message = "地址不能为空")
    private String address;

    @NotBlank(message = "微信id不能为空")
    private String openid;

    @NotBlank(message = "购物车不能为空")
    private String items;
}
