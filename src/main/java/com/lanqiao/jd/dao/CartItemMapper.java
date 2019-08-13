package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.CartItem;

public interface CartItemMapper {
    int deleteByPrimaryKey(Integer cartItemId);

    int insert(CartItem record);

    int insertSelective(CartItem record);

    CartItem selectByPrimaryKey(Integer cartItemId);

    int updateByPrimaryKeySelective(CartItem record);

    int updateByPrimaryKey(CartItem record);
}