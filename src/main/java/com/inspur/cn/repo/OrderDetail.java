package com.inspur.cn.repo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @Id
    private String id;

    private String orderId; //订单id

    private String productId; //商品id

    private String name; //商品名称

    private BigDecimal price;//商品价格

    private Integer total;//商品数量

    private String picture;//商品价格

}
