package com.inspur.cn.common.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.common.enums.Enums;
import com.inspur.cn.common.exceptions.SellException;
import com.inspur.cn.repo.OrderDetail;
import com.inspur.cn.repo.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDtoConverter {

    public static OrderDto convert(OrderForm orderForm){

        OrderDto orderDto = new OrderDto();
        Gson gson = new Gson();
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            orderDetails = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (SellException e){
            log.error("[对象转换]错误,String->{}",orderForm.getAddress());
            throw new SellException(Enums.PARAM_ERROR);
        }
        BeanUtils.copyProperties(orderForm,orderDto);
        orderDto.setOrderDetailList(orderDetails);
        return orderDto;
    }
}
