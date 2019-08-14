package com.lanqiao.jd.controller;

import com.lanqiao.jd.entity.ProductImgList;
import com.lanqiao.jd.service.ProductImgListService;
import com.lanqiao.jd.util.FileUtil;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ImagListController {
    @Autowired
    ProductImgListService productImgListService;
    @Autowired
    FileUtil fileUtil;

    @PostMapping("/insertImgList")
    public Result insertImgList(@RequestParam(name = "file") MultipartFile file,@RequestParam(name = "id") int itemId){
        String imgUrl = fileUtil.fileUpload(file, 2);
        ProductImgList productImgList = new ProductImgList();
        productImgList.setImgUrl(imgUrl);
        productImgList.setProductItemId(itemId);
        return productImgListService.insertImgList(productImgList);
    }

    @PostMapping("/deleteImgList")
    public Result deleteImgList(@RequestParam(name = "id") int imgId){
        return productImgListService.deleteImgListById(imgId);
    }


    @PostMapping("changeImgList")
    public Result changeImgList(@RequestParam(name = "record") ProductImgList productImgList){
        return productImgListService.changeImgList(productImgList);
    }

    @PostMapping("selectImgListByItemId")
    public List<ProductImgList> selectImgList(@RequestParam(name = "id") int itemId){
        return productImgListService.selectAllImgList(itemId);
    }


}
