package com.inspur.cn.repo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInfo {

    @Id
    private String id;

    //商品名称
    private String pname;

    //商品价格
    private BigDecimal price;

    //商品描述
    private String productDesc;

    //商品编号
    private Integer type;

    //库存
    private Integer stock;

    //商品图片
    private String picture;

    //商品状态0正常 1下架
    private Integer productStatus;
}
