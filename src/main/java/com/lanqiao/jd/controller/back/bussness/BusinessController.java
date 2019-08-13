package com.lanqiao.jd.controller.back.bussness;

import com.lanqiao.jd.entity.Business;
import com.lanqiao.jd.service.BusinessService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/13 17:50
 */

@RestController
public class BusinessController {
    @Autowired
    BusinessService businessService;

    @PostMapping("/insertBusiness")
    public Result insertBusiness(Business business){
//        return businessService.insertBusiness(business);
        List<String> strList = new ArrayList();
        strList.add("122");
        strList.add("122");
        strList.add("122");
        return Result.createSuccessResult(strList.size(),strList);
    }
}
