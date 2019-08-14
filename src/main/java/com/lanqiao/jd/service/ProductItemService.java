package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.ProductItem;
import com.lanqiao.jd.util.Result;

public interface ProductItemService {

    //插入productItem
    public Result insertProductItem(ProductItem productItem);

    //删除productItem

    public Result deleteProductItem(int productItemId);

    //修改productItem
    public Result changeProductItem(ProductItem productItem);

    //查询productItme

    public Result selectProductItemByProductId(int productId);
}
