package com.inspur.cn.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspur.cn.repo.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private String id;

    private String name;  //买家姓名

    private String phone; //买家电话

    private String address; //买家地址

    private String openid; //买家微信id

    private BigDecimal orderAmount; //订单总计额

    private Integer orderStatus; //订单状态 0默认新下单

    private Integer payStatus ; //支付状态 0默认未支付

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;//创建时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;//更新时间

    private List<OrderDetail> orderDetailList;
}
