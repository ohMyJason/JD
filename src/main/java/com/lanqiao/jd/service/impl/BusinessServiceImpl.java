package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.BusinessMapper;
import com.lanqiao.jd.entity.Business;
import com.lanqiao.jd.service.BusinessService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 刘佳昇
 * @Date 2019/8/13 17:52
 */

@Service
public class BusinessServiceImpl  implements BusinessService {
    @Autowired
    BusinessMapper businessMapper;


    @Override
    public Result insertBusiness(Business business) {
        try{

            int col = businessMapper.insertSelective(business);
            if (col>0){

                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("数据库错误");
            }
        }catch (Exception e){
            return  Result.createByFailure("出现错误，联系管理员");
        }
    }
}
