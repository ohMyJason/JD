package com.lanqiao.jd.controller;

import com.lanqiao.jd.entity.ProductImgList;
import com.lanqiao.jd.entity.User;
import com.lanqiao.jd.util.FileUtil;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author 刘佳昇
 * @Date 2019/8/13 15:52
 */

@RestController
public class TestController {
    @Autowired
    FileUtil fileUtil;

    @PostMapping("/test")
    public void test() throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        System.out.println("path:"+path.getAbsolutePath());
    }

    @PostMapping("/testUpload")
    public Result testUpload(@RequestParam(name = "file") MultipartFile file){
        String relaPath = fileUtil.fileUpload(file, 2);


        String absoPath = relaPath+fileUtil.rootPath;

        ProductImgList productImgList = new ProductImgList();
        productImgList.setImgUrl(relaPath);
        return  Result.createSuccessResult(relaPath);
    }
}
