package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.ProductImgListMapper;
import com.lanqiao.jd.entity.ProductImgList;
import com.lanqiao.jd.service.ProductImgListService;
import com.lanqiao.jd.util.FileUtil;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ProductImgListServiceImpl implements ProductImgListService {
    @Autowired
    ProductImgListMapper productImgListMapper;
    @Autowired
    FileUtil fileUtil;


    @Override
    public Result insertImgList(ProductImgList productImgList) {
        try{

            int col = productImgListMapper.insertSelective(productImgList);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("插入数据库错误");
            }
        }catch (Exception e){
            return  Result.createByFailure("出现错误，联系管理员");
        }
    }

    @Override
    public Result deleteImgListById(int imgId) {
        try{
            ProductImgList productImgList =new ProductImgList();

            productImgList = productImgListMapper.selectByPrimaryKey(imgId);

            String absPath = fileUtil.rootPath +"static"+ productImgList.getImgUrl();

            File file = new File(absPath);

            System.out.println(absPath);
            if(file.exists()){
                file.delete();
                System.out.println("删除成功!");
            }
            int col = productImgListMapper.deleteByPrimaryKey(imgId);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("删除错误");
            }
        }catch (Exception e){
            return  Result.createByFailure("出现错误，联系管理员");
        }
    }

//    @Override
//    public Result changeImgList(ProductImgList productImgList) {
//        try{
//
//            int col = productImgListMapper.updateByPrimaryKeySelective(productImgList);
//            if (col>0){
//                return Result.createSuccessResult();
//            }else {
//                return Result.createByFailure("插入数据库错误");
//            }
//        }catch (Exception e){
//            return  Result.createByFailure("出现错误，联系管理员");
//        }
//    }

    @Override
    public List<ProductImgList> selectAllImgList(int itemId) {
        try{
            List<ProductImgList> list = productImgListMapper.selectByItemId(itemId);
            return list;
        }catch (Exception e){
            System.out.println("出现错误");
            return null;
        }
    }
}
