package com.lanqiao.jd.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.lanqiao.jd.annotations.UserLoginToken;
import com.lanqiao.jd.entity.*;
import com.lanqiao.jd.service.*;
import com.lanqiao.jd.service.impl.CartItemServiceImpl;
import com.lanqiao.jd.util.CodeUtil;
import com.lanqiao.jd.util.Result;
import com.lanqiao.jd.util.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @Autowired
    OrderService orderService;


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
        User userForBase = userService.findByUsername(user);
        if (userForBase == null) {
            return Result.createByFailure("登录失败,用户不存在");
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                return Result.createByFailure("登录失败,密码错误");
            } else {
                String token = tokenService.getToken(userForBase);
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
    @PostMapping("/showCommentByProductId")
    public Result showCommentByItemId(@RequestParam(name = "productId") int productId){
        return commentService.showCommentByItemID(productId);
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
    //删除信息 参数：userId，producId
    @PostMapping("/deleteCartItem")
    public  Result deleteCartItem(@RequestParam(name = "userId") int userId,@RequestParam(name = "productId") int productId){
        return cartItemService.deleteCartItem(userId,productId);
    }
    //查看购物车信息
    @UserLoginToken
    @PostMapping("/showCartItem")
    public Result showCartItem(@RequestParam(name = "userId") int userId){
        return cartItemService.showCartItem(userId);
    }
    //数量+1
    @PostMapping("/addCartItemNum")
    public Result addCartItemNum(@RequestParam(name = "userId") int userId,@RequestParam(name = "productId") int productId){
        return cartItemService.addCartItemNum(userId,productId);
    }
    //数量-1
    @PostMapping("/subCartItemNum")
    public Result subCartItemNum(@RequestParam(name = "userId") int userId,@RequestParam(name = "productId") int productId){
        return cartItemService.subCartItemNum(userId,productId);
    }

    //购物车到订单过度页面展示
    @PostMapping("showItem")
    public Result showItem(@RequestParam(name = "IdArry" )String IdArry){
        String[] split = IdArry.split(",");
        int []test = new int[split.length];
        for (int i = 0; i < split.length; i++){
            test[i] = Integer.parseInt(split[i]);
        }
        return Result.createSuccessResult(orderService.showItem(test));
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

    //商品详情页
    @PostMapping("/detailPage")
    public Result detailPage(int userId, int productId){
        return productService.productItem(userId,productId);
    }


    //提交订单(先向订单表中插入订单信息，获取到orderId，然后下向)
    //need:userId userAddressId totalPrice
    //need:OrderItem(productId, num)
    @PostMapping("/order")
    public Result insertOrder( ){
//        OrderVo orderVo
//        for(OrderItem orderItem:orderVo.getOrderItem()){
//            System.out.println(orderItem.toString());
//        }
//        System.out.println(orderVo.getUserId() + " " + orderVo.getUserAddressId() + " " + orderVo.getTotalPrice() + " ");
//        return Result.createSuccessResult();

        OrderVo orderVo = new OrderVo();
        List<OrderItem> list = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setNum(2);
        orderItem.setProductId(3);
        list.add(orderItem);
        orderVo.setOrderItem(list);
        orderVo.setUserId(3);
        orderVo.setUserAddressId(2);
        BigDecimal a = new BigDecimal("253");
        orderVo.setTotalPrice(a);
        return orderService.insertOrder(orderVo);
    }

    @PostMapping("/pay")
    //need:userId  orderId
    public Result pay(Order order){
       return orderService.pay(order);
    }

    //余额充值
    @PostMapping("/addBalance")
    //need:balance userId password
    public Result addBalance(User user){
        return userService.addBalance(user);
    }


    @UserLoginToken
    @PostMapping("/getNameById")
    public Result getNameById(@RequestParam(name = "userId")int userId){
        User user =  userService.findUserById(userId);
        return Result.createSuccessResult(user.getUserName());
    }

}
