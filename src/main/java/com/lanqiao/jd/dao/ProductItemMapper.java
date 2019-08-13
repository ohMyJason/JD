package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.ProductItem;

public interface ProductItemMapper {
    int deleteByPrimaryKey(Integer productItemId);

    int insert(ProductItem record);

    int insertSelective(ProductItem record);

    ProductItem selectByPrimaryKey(Integer productItemId);

    int updateByPrimaryKeySelective(ProductItem record);

    int updateByPrimaryKey(ProductItem record);
}