($(function () {
    //设置错误信息样式
    function showCSS(){
        $(".msg-error b").css("color","red");
        $(".msg-error").css({'border-style': 'solid','border-color': '#faccc6','border-width':'thin','background-color':'#ffebeb'});
    }

    //扫码登录
    $(".login_left").click(function () {
        $(".login_code").show();
        $(".user_login").hide();
        $(".login_right .c").css({'color':'#666','font-weight':'normal'});
        $(".login_left .c").css({'color':'#F1251b','font-weight':'bold'});
    });

    //账户登录
    $(".login_right").click(function () {
        $(".user_login").show();
        $(".login_code").hide();
        $(".login_left .c").css({'color':'#666','font-weight':'normal'});
        $(".login_right .c").css({'color':'#F1251b','font-weight':'bold'});
    });

    //点击登录按钮
    $(".login_btn").click(function () {
        var user = new Object();
        user.name = $("#user_text").val();
        user.pass = $("#pwd").val();

        //非空验证
        if((user.name==""||user.name==null)&&(user.pass==""||user.pass==null)){
            showCSS();
            $(".msg-error b").html("请输入账户名和密码");
        }else if(user.pass==""||user.pass==null){
            showCSS();
            $(".msg-error b").html("请输入密码");
        }else if(user.name==""||user.name==null){
            showCSS();
            $(".msg-error b").html("请输入账户名");
        }else{
            $(".msg-error b").html("");
            //提交
            $.ajax({
                url:"/login.html",
                type:"post",
                dataType:"html",
                data:{user:JSON.stringify(user)},
                timeout:1000,
                success:function (result) {
                    if(result!=""&&result=="success"){
                        window.location.href="/index.html";
                    }else if(result=="failed"){
                        showCSS();
                        $(".msg-error b").html("登录失败，请重试！");
                        $("#user_text").val('');
                        $("#pwd").val('');
                    }else if(result=="nologincode"){
                        showCSS();
                        $(".msg-error b").html("登录账号不存在，请重试！");
                    }else if(result=="pwderror"){
                        showCSS();
                        $(".msg-error b").html("登录密码错误，请重试！");
                    }else if(result=="nodata"){
                        showCSS();
                        $(".msg-error b").html("请先注册！");
                    }
                }
            })
        }
    })
})
)