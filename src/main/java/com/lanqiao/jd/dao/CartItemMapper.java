package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartItemMapper {
    int deleteByPrimaryKey(Integer cartItemId);

    int insert(CartItem record);

    int insertSelective(CartItem record);

    CartItem selectByPrimaryKey(Integer cartItemId);

    int updateByPrimaryKeySelective(CartItem record);

    int updateByPrimaryKey(CartItem record);

    CartItem selectByProductId(@Param("shopCartId") Integer shopCartId,@Param("productId")  Integer productId);

    List<CartItem> selectByShopCartId(Integer cartItemId);
}