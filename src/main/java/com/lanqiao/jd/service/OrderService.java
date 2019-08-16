package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.OrderVo;
import com.lanqiao.jd.util.Result;


public interface OrderService {
    //向订单表和订单详情表中插入记录
    Result insertOrder(OrderVo orderVo);
}
