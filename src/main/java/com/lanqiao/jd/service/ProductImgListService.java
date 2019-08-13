package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.ProductImgList;
import com.lanqiao.jd.util.Result;

import java.util.List;

public interface ProductImgListService {
    //增加新记录
    public Result insertImgList(ProductImgList productImgList);

    //删除记录
    public Result deleteImgListById(int imgId);

    //修改新纪录
    public Result changeImgList(ProductImgList productImgList);

    //通过producitemid 查看商品信息
    public List<ProductImgList> selectAllImgList(int itemId);
}