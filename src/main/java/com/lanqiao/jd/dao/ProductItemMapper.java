package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.ProdBusiComm;
import com.lanqiao.jd.entity.ProductItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductItemMapper {
    int deleteByPrimaryKey(Integer productItemId);

    int insert(ProductItem record);

    int insertSelective(ProductItem record);

    ProductItem selectByPrimaryKey(Integer productItemId);

    int updateByPrimaryKeySelective(ProductItem record);

    int updateByPrimaryKey(ProductItem record);

    ProductItem selectByProductId(Integer productId);

}