package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.UserAddress;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Integer userAddressId);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer userAddressId);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);
}