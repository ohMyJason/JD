package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.OrderMapper;
import com.lanqiao.jd.entity.Order;
import com.lanqiao.jd.entity.OrderItem;
import com.lanqiao.jd.service.OrderService;
import com.lanqiao.jd.util.Result;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;


    //向订单和订单详情表中插入记录
    @Transactional
    @Override
    public Result insertOrder(List<OrderItem> orderItems, int userId, int userAddressId, BigDecimal totalPrice) {
        //向订单表中插入记录
        Order order = new Order();
        String date =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        order.setOrderStatus("待支付");
        order.setUserId(userId);
        order.setCreatTime(date);
        order.setUserAddressId(userAddressId);
        order.setTotalPrice(totalPrice);
        int col = orderMapper.insertSelective(order);
        int orderId = order.getOrderId();
        if(col > 0){
            return Result.createSuccessResult();
        }else{
            return Result.createByFailure("wrong");
        }
        //向订单详情表中插入记录
    }


}
