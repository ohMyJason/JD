($(function () {

    $.ajax({
        url:"http://localhost:8080/user/showCartItem",
        type:"post",
        dataType:"json",
        data:{
            userId:3
        },
        success:function (result){
            if(result.code == 0){
                // alert(result.data[0].businessName);
                // alert(result.data[0].productImgUrl);
                // alert(result.data[0].productName);
                // alert(result.data[0].detail1);
                // alert(result.data[0].productPrice);
                // $(".shop_name").html(result.data[0].businessName);
                // $(".pro-img").src("../"+result.data[0].productImgUrl);
                // $(".pro-name").html(result.data[0].productName);
                // $(".pro-detail").html(result.data[0].detail1);
                // $(".pro-price").html(result.data[0].productPrice);
                for(var i=0;i<result.count;i++){
                    var $product = $("<div class=\"cart-item-list\"> <div class=\"shop\">"+
                        "<input class=\"list-checkbox\" type=\"checkbox\">"+
                        "<a class=\"shop-name\"><span class=\"shop_name\">"+result.data[i].businessName+"</span></a> </div>"+
                    "<input class=\"list-check\" type=\"checkbox\">"+
                        "<div class=\"product-item\"> <img class=\"pro-img\" src='result.data[i].productImgUrl'>"+
                        "<span class=\"pro-name\">"+result.data[i].productName+"</span>"+
                        "<span class=\"pro-detail\">"+result.data[i].detail1+"</span>"+
                        "<p class=\"pro-price\">&yen;"+result.data[0].productPrice+"</p>"+
                    "<div class=\"pro-num\">"+
                        "<input id=\"sub-btn\" type=\"button\" value=\"-\"><input id=\"number\" type=\"text\" value=\"1\"><input id=\"add-btn\" type=\"button\" value=\"+\"></div>"+
                        "<b class=\"total-price pro-price\">&yen;"+result.data[0].productPrice*$("#number").val()+"</b>"+
                    "<a><span id=\"delete\">删除</span></a><br>"+
                    "<a><span id=\"move\">移到我的关注</span></a> </div> </div>");
                    $("#jd-cart").append($product);
                }
            }else{
                alert("error");
            }
            },
        error:function () {
            // alert("失败");
        }
    });

        //添加数量
        $("#add-btn").click(function () {
            $("#number").val(parseInt($("#number").val())+1);
            $("#number").trigger("input");
            $(".total-price").val(parseInt($("#number").val())*parseInt($(".pro-price").val()));
        });

        //减少数量
        $("#sub-btn").click(function () {
            var num = parseInt($("#number").val());
            if(num>1){
                $("#number").val(num-1);
            }else{
                $("#number").val(1);
            }
        })
})
)