package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.Business;
import com.lanqiao.jd.util.Result;
import org.springframework.stereotype.Repository;

/**
 * @author 刘佳昇
 * @Date 2019/8/13 17:51
 */
public interface BusinessService {
    //后台->添加商家
    Result insertBusiness(Business business);

    //后台->删除商家
    Result deleteBusiness(int businessId);

    //后台->更改商家信息（need：businessId & businessName）
    Result updateBusiness(Business business);

    //后台->根据id查询商品的信息
    Result selectBusinessById(int businessId);

    //后台->查询所有商家的信息
    Result selectAllBusiness();
}
