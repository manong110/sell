package com.inspur.cn.repository;

import com.inspur.cn.repo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryCategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findOneTest(){
        Optional<Category> repository = categoryRepository.findById(1);
        System.out.println(repository);
    }

    @Test
    public void saveTest(){
        Category category = new Category("女生最爱",4);
        categoryRepository.save(category);
    }

    @Test
    @Transactional
    public void uppdateTest(){
        Category category = new Category("男生最爱",5);
        category.setId(2);
        categoryRepository.save(category);

    }

}