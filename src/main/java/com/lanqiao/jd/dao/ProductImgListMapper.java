package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.ProductImgList;

public interface ProductImgListMapper {
    int deleteByPrimaryKey(Integer imgId);

    int insert(ProductImgList record);

    int insertSelective(ProductImgList record);

    ProductImgList selectByPrimaryKey(Integer imgId);

    int updateByPrimaryKeySelective(ProductImgList record);

    int updateByPrimaryKey(ProductImgList record);
}