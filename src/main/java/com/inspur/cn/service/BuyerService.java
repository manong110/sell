package com.inspur.cn.service;

import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.repo.form.ParamForm;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public interface BuyerService {

    public OrderDto findOrderOne(@Valid ParamForm paramForm, BindingResult result);

    public OrderDto cancelOrder(@Valid ParamForm paramForm, BindingResult result);
}
