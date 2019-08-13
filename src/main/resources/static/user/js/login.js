($(function () {
    //扫码登录
    $(".login_left").click(function () {
        $(".login_code").show();
        $(".user_login").hide();
    });

    //账户登录
    $(".login_right").click(function () {
        $(".user_login").show();
        $(".login_code").hide();
    });

    //验证
    // $("#user_text").blur(function () {
    //     $(this).next(".red").remove();
    //     if ($("#user_text").val()!=($(this).val())){
    //         var $errorMsg = $(".msg-error").html()
    //     }
    // })
})
)