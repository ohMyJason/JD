($(function () {

    show();

    $.ajax({
        url:"/user/getNameById",
        type:"post",
        dataType: "json",
        headers:{'token':$.cookie("token")
        },
        data:{
            userId: $.cookie('userId')
        },
        success: function (result) {
            if (result.code==0) {
               $("#shortCut_right").children().eq(0).html(result.data);
            }
        }
    });
    //计算商品数
    function proNum() {
        var pronum = 0;
        $(".change_box").each(function () {
            var num = parseInt($(this).children().eq(1).find("#dcr_t_num").children().html());
            pronum += parseInt(num);
        });
        $("#productNum").html(parseInt(pronum));
    }

    //总商品金额
    function totalPrice() {
        var totalPrice = 0;
        $(".change_box").each(function () {
            var num = parseInt($(this).children().eq(1).find("#dcr_t_num").children().html());
            var price = parseInt($(this).children().eq(1).find("#dcr_t_mid").children().eq(0).html());
            var eachPrice = parseInt(num)*parseInt(price);
            totalPrice += parseInt(eachPrice);
        })
        $("#productPrice").html(parseInt(totalPrice));
        $("#realPay").html(parseInt(totalPrice)+8);
    }


    function show() {
            $.ajax({
                url: "http://localhost:8080/user/showItem",
                type: "post",
                traditional:true,
                dataType: "json",
                headers:{'token':$.cookie("token")
                },
                data: {
                    IdArry: $.cookie('IdArry')
                },
                success: function (result) {
                    if(result.code == 0){
                        var $node = $(".change_box").clone(true);
                        if(result.count>=2){
                            for(var i = 1; i<result.count; i++){
                                if(i > 1){
                                    //最外：checkutStep
                                    var h = $("#checkoutSteps").height();
                                    h+=190;
                                    $("#checkoutSteps").height(h);
                                    //中间：deliver
                                    h = $("#deliver").height();
                                    h += 165;
                                    $("#deliver").height(h);
                                    //中下:deliver_cont
                                    h = $("#deliver_cont").height();
                                    h+=145;
                                    $("#deliver_cont").height(h);
                                }

                                //最内:deliver_cont_right
                                h = $("#deliver_cont_right").height();
                                h+=145;
                                $("#deliver_cont_right").height(h);
                            }

                        }
                        $(".change_box").detach();
                        for(var i=0; i < result.count; i++){
                            $node.children().eq(0).html(result.data[i].businessName);
                            var imgsrc = ".." + result.data[i].productImgUrl;
                            $node.children().eq(1).find("img").attr("src",imgsrc);
                            $node.children().eq(1).find("#dcr_t_left").children("ul").children("li").eq(0).html(result.data[i].productName);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(0).html(result.data[i].productPrice);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(1).html(result.data[i].detail1);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(2).html(result.data[i].productId);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(3).html(result.data[i].cartItemId);
                            $node.children().eq(1).find("#dcr_t_num").children().html(result.data[i].num);
                            $("#deliver_cont_right").append($node);
                            $node = $(".change_box").eq(0).clone(true);
                        }
                        proNum();
                        totalPrice();
                    }else{
                        alert("请先登录！");
                        window.location.href="/user/login.html";
                    }
                },error: function () {
                    alert("请求失败！");
                }
            });
        }

        //点击显示，再点击隐藏
        $("#more_addr").click(function () {
            if($("#no_display").is(':hidden')) {
                $("#no_display").show();
            }else{
                $("#no_display").hide();
            }
        })

        //显示地址
        $.ajax({
            url:"/user/getAddress",
            type:"post",
            dataType:"json",
            // headers:{'token':$.cookie("token")
            // },
            data:{
                userId: $.cookie('userId')
            },
            success:function (result) {
                if(result.code == 0){
                    //第一个默认地址
                    var $model = $("#cont_addr").clone(true);
                    $("#cont_addr").detach();
                    $model.children().eq(0).find("span").html(result.data[0].receiveName);
                    $model.children().eq(0).next().children().children().eq(0).html(result.data[0].receiveName);
                    $model.children().eq(0).next().children().children().eq(1).html(result.data[0].address);
                    $model.children().eq(0).next().children().children().eq(2).html(result.data[0].receiveTel);
                    $("#no_display").before($model);

                    var $node = $("#cont_addr").clone(true);
                    for(var i=1; i < result.count; i++){
                        $node.children().eq(0).find("span").html(result.data[i].receiveName);
                        $node.children().eq(0).next().children().children().eq(0).html(result.data[i].receiveName);
                        $node.children().eq(0).next().children().children().eq(1).html(result.data[i].address);
                        $node.children().eq(0).next().children().children().eq(2).html(result.data[i].receiveTel);
                        $node.children().eq(0).next().children().eq(1).hide();   //新节点默认地址隐藏
                        $("#no_display").append($node);
                        $node = $("#cont_addr").clone();
                    }

                }else{
                    alert("请先登录！");
                    window.location.href="/user/login.html";
                }
            },error:function () {
                alert("请求失败！");
            }
        })

        //提交订单
        $("input[name='submitOrder']").click(function () {
            $.ajax({
                url:"/user/order",
                type:"post",
                dataType:"json",
                data:{
                    userId: $.cookie('userId'),
                    userAddressId: 1,
                    totalPrice: $("#realPay").html(),
                    IdArry: $.cookie('IdArry')
                },success:function (result) {
                    if (result.code == 0){
                        $.cookie("orderId",result.data.orderId,{path:'/user/pay.html'});
                        $.ajax({
                            url:"/user/deleteWhenCreateOrder",
                            type:"post",
                            dataType: "json",
                            data:{
                                IdArry: $.cookie('IdArry')
                            },success:function (result) {
                                if(result.code!= 0){
                                    alert("删除购物车信息失败！");
                                }
                            },error:function () {
                                alert("删除购物车信息的请求失败！")
                            }
                        })

                        window.location.href = "/user/pay.html";
                    } else {
                        alert("向订单插入出错！")
                    }
                },error:function () {
                    alert("向服务器请求失败！");
                }
            })
        })


    })
)