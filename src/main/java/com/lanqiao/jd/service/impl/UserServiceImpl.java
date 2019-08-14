package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.UserMapper;
import com.lanqiao.jd.entity.User;
import com.lanqiao.jd.service.UserService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    //实现注册功能
    @Override
    public Result register(User user) {
        try{
            int col =  userMapper.insertSelective(user);
            if(col>0){
                return Result.createSuccessResult();
            }
            else {
                return Result.createByFailure("数据库错误");
            }
        }catch(Exception e){
            return Result.createByFailure("出现错误，可能是用户已存在或手机号已经被注册");
        }

    }

    //登录
    @Override
    public Result login(User user) {
        try {
            User record = userMapper.selectUserByUserNameAndPassword(user);
            if(record  != null){
                return Result.createSuccessResult();
            }
            else{
                return Result.createByFailure("用户名或密码错误");
            }
        }catch(Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }
    }

    @Override
    public User findUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User findByUsername(User user) {
        return userMapper.selectByUserName(user);
    }
}
