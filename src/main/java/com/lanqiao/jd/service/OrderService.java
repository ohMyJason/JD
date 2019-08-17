package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.Order;
import com.lanqiao.jd.entity.OrderVo;
import com.lanqiao.jd.util.Result;


public interface OrderService {
    //向订单表和订单详情表中插入记录
    Result insertOrder(OrderVo orderVo);

    //支付->检查用户余额->减少用户余额->更改订单状态为已支付
    Result pay(Order order);
}
