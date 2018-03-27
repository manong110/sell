package com.inspur.cn.repo;

import com.inspur.cn.common.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMaster {

    @Id
    private String id;

    private String name;  //买家姓名

    private String phone; //买家电话

    private String address; //买家地址

    private String openid; //买家微信id

    private BigDecimal orderAmount; //订单总计额

    private Integer orderStatus= Enums.NEW.getCode(); //订单状态 0默认新下单

    private Integer payStatus=Enums.WAIT.getCode(); //支付状态 0默认未支付

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

//    @Transient
//    private List<OrderDetail> orderDetailList;

}

