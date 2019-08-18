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








    function show() {
            $.ajax({
                url: "http://localhost:8080/user/showItem",
                type: "post",
                traditional:true,
                dataType: "json",
                headers:{'token':$.cookie("token")
                },
                data: {
                    IdArry: ['1','3','4','5','6']
                },
                success: function (result) {
                    if(result.code == 0){
                        var $node = $("#change_box").clone(true);
                        $("#change_box").eq(0).detach();
                        for(var i=0; i < result.count; i++){
                            $node.children().eq(0).html(result.data[i].businessName);
                            var imgsrc = ".." + result.data[i].productImgUrl;
                            $node.children().eq(1).find("img").attr("src",imgsrc);
                            $node.children().eq(1).find("#dcr_t_left").children("ul").children("li").eq(0).html(result.data[i].productName);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(0).html(result.data[i].productPrice);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(1).html(result.data[i].detail1);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(2).html(result.data[i].productId);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(3).html(result.data[i].cartItemId);
                            $node.children().eq(1).find("#dcr_t_num").children().html("x" + result.data[i].num);
                            $("#deliver_cont_right").append($node);
                             $node = $("#change_box").eq(0).clone(true);
                        }
                    }else{
                        alert("请先登录！");
                        window.location.href="/user/login.html";
                    }
                },error: function () {
                    alert("请求失败！");
                }
            })
        }
    })
)