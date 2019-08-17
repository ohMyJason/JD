($(function () {
    //支付
    $("#payButton").click(function () {
        $.ajax({
            url:"http://localhost:8080/user/pay",
            type:"post",
            dataType:"json",
            data:{
                userId:3, //TODO
                orderId:5, //TODO
            },
            success:function (result){
                //余额不够
                if(result.code == -100)
                {
                    var msg = result.msg;

                    if(msg == "您已支付，请勿重复支付"){
                        alert(msg + ",即将跳转到主页");
                        window.location.href = "http://localhost:8080/user/index.html"
                    }
                    else if(msg == "余额不足，请充值")
                    {
                        $("#balanceBox").css("display","block");
                    }
                }
                else if (result.code == 0){
                    // alert("支付成功,即将跳转到主页");
                    $(".w").hide();
                    $("#success").hide();
                    $("#payButton").hide();
                    $("#paySuccess").show();
                    // window.location.href = "http://localhost:8080/user/index.html"
                }
            }
        })
    })

    //充值
    $("#addMoney").click(function () {
        $.ajax({
            url:"http://localhost:8080/user/addBalance",
            type:"post",
            dataType:"json",
            data:{
                balance:$("#money").val(),
                userId:3, //TODO
                password:$("#pwd").val()
            },
            success:function (result){
                if(result.code == 0){
                    $("#balanceBox").hide();
                    $("#success").show();
                }
                else if(result.code == -100){

                }
                else{
                    alert("充值失败，稍后重试");
                }
            }
        })
    })

    //获取用户名
    var name;
    $.ajax({
        url:"http://localhost:8080/user/getNameById",
        type:"post",
        dataType:"json",
        data:{
            userId:3, //TODO
        },
        success:function (result) {
            name = result.data;
            //更改用户名
            $("#name").html(name);
        }
    })


    //更改支付金额
    $("#payMoney").html(100);




}))