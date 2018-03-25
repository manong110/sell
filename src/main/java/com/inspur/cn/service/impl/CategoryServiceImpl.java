package com.inspur.cn.service.impl;

import com.inspur.cn.repo.Category;
import com.inspur.cn.repository.CategoryRepository;
import com.inspur.cn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findOne(Integer id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findByTypeIn(List<Integer> typeList) {
        return categoryRepository.findByTypeIn(typeList);
    }
}
