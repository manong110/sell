package com.inspur.cn.controller;

import com.inspur.cn.common.converter.OrderForm2OrderDtoConverter;
import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.common.dto.ResultDto;
import com.inspur.cn.common.dto.ResultUtil;
import com.inspur.cn.common.enums.Enums;
import com.inspur.cn.common.exceptions.SellException;
import com.inspur.cn.repo.form.OrderForm;
import com.inspur.cn.repo.form.ParamForm;
import com.inspur.cn.service.OrderService;
import com.inspur.cn.service.ProductInfoService;
import com.inspur.cn.service.impl.BuyerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerServiceImpl buyerService;

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

    /**
     * 获取订单列表
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultDto<List<OrderDto>> findByOpenid(@RequestParam(value = "openid") String openid,
                                                  @RequestParam(value = "page",defaultValue = "0")Integer page,
                                                  @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("[查询订单列表]openid不能为空");
            throw new SellException(Enums.PARAM_ERROR);
        }
        PageRequest request = PageRequest.of(page,size);
        Page<OrderDto> dtoPage = orderService.findByOpenid(openid, request);
        return ResultUtil.success(dtoPage.getContent());
    }

    /**
     * 查询订单详情
     * @param paramForm
     * @param result
     * @return
     */
    @GetMapping("/detail")
    public ResultDto<OrderDto> findOrder(@Valid ParamForm paramForm,BindingResult result) {
        return ResultUtil.success(buyerService.findOrderOne(paramForm,result));
    }

    @PostMapping("/cancel")
    public ResultDto cancel(@Valid ParamForm paramForm,BindingResult result) {
        buyerService.cancelOrder(paramForm,result);
        return ResultUtil.success();
    }

}