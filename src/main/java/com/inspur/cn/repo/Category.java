package com.inspur.cn.repo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@DynamicUpdate
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    /*类目名称*/
    private String name;

    /*类目编号*/
    private Integer type;

    public Category(String name, Integer type) {
        this.name = name;
        this.type = type;
    }
}
