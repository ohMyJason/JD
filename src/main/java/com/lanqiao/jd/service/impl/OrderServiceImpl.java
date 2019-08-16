package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.OrderItemMapper;
import com.lanqiao.jd.dao.OrderMapper;
import com.lanqiao.jd.entity.Order;
import com.lanqiao.jd.entity.OrderItem;
import com.lanqiao.jd.entity.OrderVo;
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

    @Autowired
    OrderItemMapper orderItemMapper;

    //向订单和订单详情表中插入记录
    @Transactional
    @Override
    public Result insertOrder(OrderVo orderVo) {
        //向订单表中插入记录
        Order order = new Order();
        String date =  new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        order.setOrderStatus("待支付");
        order.setUserId(orderVo.getUserId());
        order.setCreatTime(date);
        order.setUserAddressId(orderVo.getUserAddressId());
        order.setTotalPrice(orderVo.getTotalPrice());
        int col = orderMapper.insertSelective(order);
        //获取orderId
        int orderId = order.getOrderId();
        if(col > 0){
            //向订单详情表中插入记录->订单详情有多条
            //orderId dproductId num
            for(OrderItem orderItem:orderVo.getOrderItem()){
                OrderItem insetOrderItem = new OrderItem();
                insetOrderItem.setOrderId(orderId);
                insetOrderItem.setProductId(orderItem.getProductId());
                insetOrderItem.setNum(orderItem.getNum());
                orderItemMapper.insertSelective(insetOrderItem);
            }
            return Result.createSuccessResult();
        }else{
            return Result.createByFailure("数据库错误，联系管理员");
        }
    }
}
