package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.ProductMapper;
import com.lanqiao.jd.entity.Product;
import com.lanqiao.jd.service.ProductService;
import com.lanqiao.jd.util.FileUtil;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    FileUtil fileUtil;


    //添加product
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
    //根据producid删除product
    @Override
    public Result deleteProduct(int productId) {
        try{
            Product product =new Product();

            product = productMapper.selectByPrimaryKey(productId);

            String absPath = fileUtil.rootPath +"static"+ product.getProductImgUrl();

            File file = new File(absPath);

            System.out.println(absPath);
            if(file.exists()){
                file.delete();
                System.out.println("删除成功!");
            }
            int col = productMapper.deleteByPrimaryKey(productId);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("删除错误");
            }
        }catch (Exception e){
            return  Result.createByFailure("出现错误，联系管理员");
        }
    }
    //根据business查询所有
    @Override
    public Result selectProduct(int productId) {
        try {
            List<Product> productList = productMapper.selectAllProductByBusinessId(productId);
            return Result.createSuccessResult(productList.size(),productList);
        }catch (Exception e){
            return Result.createByFailure("查询异常");
        }

    }


}
