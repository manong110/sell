package com.inspur.cn.service.impl;

import com.inspur.cn.common.dto.CartDto;
import com.inspur.cn.common.dto.OrderDto;
import com.inspur.cn.common.enums.Enums;
import com.inspur.cn.common.exceptions.SellException;
import com.inspur.cn.common.util.KeyUtil;
import com.inspur.cn.repo.OrderDetail;
import com.inspur.cn.repo.OrderMaster;
import com.inspur.cn.repo.ProductInfo;
import com.inspur.cn.repository.OrderDetailRepository;
import com.inspur.cn.repository.OrderMasterRepository;
import com.inspur.cn.service.OrderService;
import com.inspur.cn.service.ProductInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {

        //数量初始值
        BigDecimal bigDecimal = new BigDecimal(BigInteger.ZERO);
        //key值
        String orderId = KeyUtil.getKeyValue();

        //查询商品数量和单价
        for(OrderDetail orderDetail : orderDto.getOrderDetailList()){

            Optional<ProductInfo> info = productInfoService.findById(orderDetail.getOrderId());

            if(info==null){
                throw new SellException(Enums.PRODUTC_NOT_EXIST);
            }
            //计算价格
            bigDecimal = info.get().getPrice().multiply(new BigDecimal(orderDetail.getTotal()))
                    .add(bigDecimal);

            //订单详情入库
            ProductInfo productInfo = info.get();
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setId(KeyUtil.getKeyValue());
            orderDetail.setName(productInfo.getPname());
            orderDetailRepository.save(orderDetail);

        }

        //写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setId(orderId);
        orderMaster.setOrderAmount(bigDecimal);
        orderMaster.setOrderStatus(Enums.NEW.getCode());
        orderMaster.setPayStatus(Enums.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //减库存
        List<CartDto> cartDtoList =
        orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getOrderId(),e.getTotal()))
                .collect(Collectors.toList());

        productInfoService.decreaseStork(cartDtoList);
        return orderDto;
    }

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    @Override
    public OrderDto findOrder(String orderId) {

        Optional<OrderMaster> orderMaster = orderMasterRepository.findById(orderId);
        OrderMaster master = orderMaster.get();
        if(master == null){
            throw new SellException(Enums.ORDER_MASTER);
        }
        List<OrderDetail> detailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(detailList)){
            throw new SellException(Enums.ORDER_DETAIL);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(master,orderDto);
        orderDto.setOrderDetailList(detailList);
        return orderDto;
    }

    /**
     * 查询订单列表
     * @param openid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderMaster> findByOpenid(String openid, Pageable pageable) {
        PageRequest request = PageRequest.of(0,30);
        return orderMasterRepository.findByOpenid(openid, request);
    }

    /**
     * 取消订单
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto cancel(OrderDto orderDto) {

        return null;
    }

    /**
     * 完结订单
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    /**
     * 支付订单
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
