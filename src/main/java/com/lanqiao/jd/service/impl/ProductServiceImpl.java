package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.ProductMapper;
import com.lanqiao.jd.entity.Product;
import com.lanqiao.jd.service.ProductService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public Result insertProduct(Product product) {
        try{

            int col = productMapper.insertSelective(product);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("插入数据库错误");
            }
        }catch (Exception e){
            return  Result.createByFailure("出现错误，联系管理员");
        }
    }
}
