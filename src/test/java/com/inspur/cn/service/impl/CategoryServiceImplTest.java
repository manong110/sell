package com.inspur.cn.service.impl;

import com.inspur.cn.repo.Category;
import com.inspur.cn.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws Exception {
        Category category = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),category.getId());
    }

    @Test
    public void findAll() throws Exception {
        List<Category> all = categoryService.findAll();
        Assert.assertNotNull(all);
    }

    @Test
    @Transactional
    public void save() throws Exception {
        Category category = new Category("畅销榜",3);
        Category save = categoryService.save(category);
        Assert.assertNotNull(save);

    }

    @Test
    public void findByTypeIn() throws Exception {
        List<Integer> typeList = new ArrayList<Integer>();
        typeList.add(1);
        typeList.add(2);
        typeList.add(3);
        List<Category> list = categoryService.findByTypeIn(typeList);
        Assert.assertNotNull(list);
    }

}