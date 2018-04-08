package com.inspur.cn.controller;

import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.common.enums.Enums;
import com.inspur.cn.common.exceptions.SellException;
import com.inspur.cn.service.OrderService;
import com.inspur.cn.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 支付
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    /**
     * 微信创建订单
     * @param orderId
     * @param resultUrl
     * @param map
     * @return
     */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("resultUrl")String resultUrl,
                               Map<String ,Object>map){
        OrderDto order = orderService.findOrder(orderId);
        if (order==null){
            log.error("[订单信息]查找不到订单信息 orderid->{}",orderId);
            throw new SellException(Enums.ORDER_DETAIL);
        }
        PayResponse response = payService.create(order);
        map.put("response",response);
        map.put("resultUrl",resultUrl);
        return new ModelAndView("pay/create",map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    @ResponseBody
    public ModelAndView notify(String notifyData){
        payService.notify(notifyData);
        return new ModelAndView("/pay/success");//通知微信支付成功
    }

}
