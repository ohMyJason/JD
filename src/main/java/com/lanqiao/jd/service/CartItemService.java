package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.CartItem;
import com.lanqiao.jd.util.Result;

public interface CartItemService {
    //向购物车插入一条信息
    public Result insertCartItem(int userId, CartItem cartItem);
    //删除一条信息
    public Result deleteCartItem(int userId, int productId);
    //展示购物车信息
    public Result showCartItem(int userId);
}
