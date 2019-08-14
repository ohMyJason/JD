package com.lanqiao.jd.service;


import com.lanqiao.jd.entity.Product;
import com.lanqiao.jd.util.Result;

public interface ProductService {
    //添加product
    public Result insertProduct(Product product);

    //删除product
    public Result delectProduct(int productId);

    //查看product
    public Product selectProduct(int productId);
}
