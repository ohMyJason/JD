package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.User;
import com.lanqiao.jd.util.Result;

public interface UserService {
    //注册用户->向User表中插入数据
    Result register(User user);

    //登录用户->检查用户名密码是否有匹配值
    Result login(User user);

    User findUserById(Integer userId);

    User findByUsername(User user);
}
