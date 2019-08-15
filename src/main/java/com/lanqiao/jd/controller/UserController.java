package com.lanqiao.jd.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.lanqiao.jd.annotations.UserLoginToken;
import com.lanqiao.jd.entity.CartItem;
import com.lanqiao.jd.entity.Comment;
import com.lanqiao.jd.entity.Product;
import com.lanqiao.jd.entity.User;
import com.lanqiao.jd.service.*;
import com.lanqiao.jd.service.impl.CartItemServiceImpl;
import com.lanqiao.jd.util.CodeUtil;
import com.lanqiao.jd.util.Result;
import com.lanqiao.jd.util.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.lanqiao.jd.util.CodeUtil.getNewcode;
import static com.lanqiao.jd.util.CodeUtil.setNewcode;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    TokenService tokenService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    ProductService productService;
    @Autowired
    SmsUtils smsUtils;

    @Autowired
    CodeUtil codeUtil;


    //注册验证手机号是否存在
    @PostMapping("/verifyPhoneNumber")
    public Result verifyPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber){
        return userService.verifyPhoneNumber(phoneNumber);
    }


    //注册功能->向数据库中插入一条user记录
    //need:userName password  phoneNumber
    @PostMapping("/register")
    public Result register(User user) {
        return userService.register(user);
    }

    //    @PostMapping("/login")
//    public Result login(User user){
//        return userService.login(user);
//    }


    //登录 -> 检查用户名密码与数据库中的记录是否匹配
    @PostMapping("/login")
    public Result login(User user) {
//        JSONObject jsonObject = new JSONObject();
        User userForBase = userService.findByUsername(user);
        if (userForBase == null) {
//            jsonObject.put("message", "登录失败,用户不存在");
//            return jsonObject;
            return Result.createByFailure("登录失败,用户不存在");
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
//                jsonObject.put("message", "登录失败,密码错误");
//                return jsonObject;
                return Result.createByFailure("登录失败,密码错误");
            } else {
                String token = tokenService.getToken(userForBase);
//                jsonObject.put("token", token);
//                jsonObject.put("user", userForBase);
//                return jsonObject;
                List listObject = new ArrayList();
                listObject.add(userForBase);
                listObject.add("token:"+token);
                return Result.createSuccessResult(2,listObject);
            }
        }
    }

    @UserLoginToken
    @PostMapping("/getMessage")
    public String getMessage(){
        try {
            return "你已通过验证";
        }catch (Exception e){
            return "请登录";
        }
    }



    //评论相关
    @PostMapping("/insertComment")
    public Result insertComment(Comment comment){return commentService.insertComment(comment);}
    //展示某个商品的评论
    @PostMapping("/showCommentByItemId")
    public Result showCommentByItemId(@RequestParam(name = "productItemId") int productItemId){
        return commentService.showCommentByItemID(productItemId);
    }


    //主页->搜索栏：根据商品名称模糊查询
    //need:name
    @PostMapping("/fuzzyQueryProduct")
    public Result fuzzyQueryProduct(String name){
        return productService.fuzzyQueryProduct(name);
    }

    //购物车相关
    //插入一条信息 参数：userId ，cartItem
    @PostMapping("/insertCartItem")
    public Result insertCartItem(@RequestParam(name = "userId") int userId, CartItem cartItem){
        return cartItemService.insertCartItem(userId,cartItem);
    }
    //删除信息 参数：user，producId
    @PostMapping("/deleteCartItem")
    public  Result deleteCartItem(@RequestParam(name = "userId") int userId,@RequestParam(name = "productId") int productId){
        return cartItemService.deleteCartItem(userId,productId);
    }


    //发送手机验证码
    @PostMapping("/sendMsg")
    //need:phoneNumber
    public Result sendMsg(@RequestParam(name = "phoneNumber") String phoneNumber){
        setNewcode();
        String code = Integer.toString(getNewcode());
        try{
            SendSmsResponse sendSms =smsUtils.sendSms(phoneNumber,code);//填写你需要测试的手机号码
            System.out.println("短信接口返回的数据----------------");
            System.out.println("Code=" + sendSms.getCode());
            System.out.println("Message=" + sendSms.getMessage());
            System.out.println("RequestId=" + sendSms.getRequestId());
            System.out.println("BizId=" + sendSms.getBizId());
            return Result.createSuccessResult();
        }catch(Exception e){
            return Result.createByFailure();
        }
    }
}
