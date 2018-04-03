package com.inspur.cn.controller;

import com.inspur.cn.common.converter.OrderForm2OrderDtoConverter;
import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.common.dto.ResultDto;
import com.inspur.cn.common.dto.ResultUtil;
import com.inspur.cn.common.enums.Enums;
import com.inspur.cn.common.exceptions.SellException;
import com.inspur.cn.repo.form.OrderForm;
import com.inspur.cn.service.OrderService;
import com.inspur.cn.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 创建订单
     * @param orderForm
     * @param result
     * @return
     */
    @PostMapping("/create")
    public ResultDto createOrder(@Valid OrderForm orderForm ,BindingResult result) {
        if(result.hasErrors()){
            log.error("[创建订单]参数不正确 orderForm->{}",orderForm);
            String message = result.getFieldError().getDefaultMessage();
            throw new SellException(Enums.PARAM_ERROR.getCode(),message);
        }
        OrderDto orderDto = OrderForm2OrderDtoConverter.convert(orderForm);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderService.createOrder(orderDto).getId());
        return ResultUtil.success(map);
    }


}
