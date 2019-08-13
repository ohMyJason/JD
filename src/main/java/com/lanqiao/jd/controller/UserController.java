package com.lanqiao.jd.controller;

import com.lanqiao.jd.dao.UserMapper;
import com.lanqiao.jd.entity.User;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 刘佳昇
 * @Date 2019/8/13 14:39
 */
@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public Result login(User user){
        return null;
    }



}
