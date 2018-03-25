package com.inspur.cn.service;

import com.inspur.cn.repo.Category;

import java.util.List;

/**
 * 类目
 */
public interface CategoryService {

    Category findOne(Integer id);

    List<Category> findAll();

    Category save(Category category);

    List<Category> findByTypeIn(List<Integer> typeList);
}
