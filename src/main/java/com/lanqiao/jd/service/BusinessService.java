package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.Business;
import com.lanqiao.jd.util.Result;
import org.springframework.stereotype.Repository;

/**
 * @author 刘佳昇
 * @Date 2019/8/13 17:51
 */
public interface BusinessService {
    Result insertBusiness(Business business);
}
