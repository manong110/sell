package com.inspur.cn.service.impl;

import com.inspur.cn.common.converter.OrderMaster2OrderDtoConverter;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
@Slf4j
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

            Optional<ProductInfo> info = productInfoService.findById(orderDetail.getProductId());

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
            orderDetail.setOrderId(orderId);
            orderDetail.setName(productInfo.getPname());
            orderDetailRepository.save(orderDetail);

        }

        //写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setId(orderId);
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(bigDecimal);
        orderMaster.setOrderStatus(Enums.NEW.getCode());
        orderMaster.setPayStatus(Enums.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //减库存
        List<CartDto> cartDtoList =
        orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(),e.getTotal()))
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
    public Page<OrderDto> findByOpenid(String openid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByOpenid(openid, pageable);

        List<OrderDto> orderDtoList = OrderMaster2OrderDtoConverter.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtoList,pageable,orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    /**
     * 取消订单
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {

        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if(!Enums.NEW.getCode().equals(orderDto.getOrderStatus())){
            log.error("[取消订单] 订单状态不正确, orderId->{},orderStatus->{}",orderDto.getId(),orderDto.getOrderStatus());
            throw new SellException(Enums.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(Enums.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster master = orderMasterRepository.save(orderMaster);
        if( master==null ){
            log.error("[取消订单] 更新失败,master->{}",master);
            throw new SellException(Enums.ORDER_UPPDATE_ERROR);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("[取消订单] 订单中无商品详情。orderDto->{}",orderDto);
            throw new SellException(Enums.ORDER_XQ);
        }
        List<CartDto> list = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(),e.getTotal()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(list);
        //如果已支付，需要退款
        if(Enums.SUCCESS.getCode().equals(master.getPayStatus())){
            //TODO
        }
        return orderDto;
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
