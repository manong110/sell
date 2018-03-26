package com.inspur.cn.controller;

import com.inspur.cn.common.dto.ResultDto;
import com.inspur.cn.common.dto.ResultUtil;
import com.inspur.cn.common.enums.Enums;
import com.inspur.cn.repo.Category;
import com.inspur.cn.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResultDto save(@Valid Category category, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String msg = bindingResult.getFieldError().getDefaultMessage();
            log.error("错误信息:[{}]",msg);
            return ResultUtil.error(100,msg);
        }
        return ResultUtil.success(categoryService.save(category));
    }
}
