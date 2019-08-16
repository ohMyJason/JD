package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.OrderItem;
import com.lanqiao.jd.util.Result;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    //向订单表和订单详情表中插入记录
    Result insertOrder(List<OrderItem> orderItems,int userId, int userAddressId, BigDecimal totalPrice );
}
