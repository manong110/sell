package com.inspur.cn.common.dto;

import com.inspur.cn.repo.OrderDetail;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderDto {

    private String id;

    private String name;  //买家姓名

    private String phone; //买家电话

    private String address; //买家地址

    private String openid; //买家微信id

    private BigDecimal orderAmount; //订单总计额

    private Integer orderStatus; //订单状态 0默认新下单

    private Integer payStatus ; //支付状态 0默认未支付

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    private List<OrderDetail> orderDetailList;
}
