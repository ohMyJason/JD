package com.lanqiao.jd.controller;

import com.lanqiao.jd.entity.Comment;
import com.lanqiao.jd.entity.User;
import com.lanqiao.jd.service.CommentService;
import com.lanqiao.jd.service.UserService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    //注册功能->向数据库中插入一条user记录
    //need:userName password  phoneNumber
    @PostMapping("/register")
    public Result register(User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(User user){
        return userService.login(user);
    }





    //评论相关
    @PostMapping("/insertComment")
    public Result insertComment(Comment comment){return commentService.insertComment(comment);}
    //展示某个商品的评论
    @PostMapping("/showCommentByItemId")
    public Result showCommentByItemId(@RequestParam(name = "productItemId") int productItemId){
        return commentService.showCommentByItemID(productItemId);
    }
}