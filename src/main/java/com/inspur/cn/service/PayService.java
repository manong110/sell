package com.inspur.cn.service;

import com.inspur.cn.common.dto.OrderDto;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.web.bind.annotation.ResponseBody;

public interface PayService {

    PayResponse create(OrderDto orderDto);

    PayResponse notify(String notifyData);
}
