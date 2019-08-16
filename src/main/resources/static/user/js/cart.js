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
                var $node =  $(".cart-item-list").detach();
                for(var i=0;i<result.count;i++){
                    $node.children().eq(0).find("a").children().html(result.data[i].businessName);
                    var imgsrc = ".." + result.data[i].productImgUrl;
                    var $temp = $node.children().eq(2).children();
                    $temp.eq(0).attr("src",imgsrc);
                    $temp.eq(1).html(result.data[i].productName);
                    $temp.eq(2).html(result.data[i].detail1);
                    $temp.eq(3).html(result.data[i].productPrice);
                    $("#jd-cart").append($node);
                    $node = $("#jd-cart").children().eq(1).clone();
                }
                // for(var i=0;i<result.count;i++){
                //     $(".shop_name").html(result.data[i].businessName);
                //     var imgsrc = ".." + result.data[i].productImgUrl;
                //     $(".pro-img").attr("src",imgsrc);
                //     $(".pro-name").html(result.data[i].productName);
                //     $(".pro-detail").html(result.data[i].detail1);
                //     $(".pro-price").html(result.data[i].productPrice);
                //     $("#jd-cart").append($model);
                //     $model = $("#jd-cart").children().eq(1).clone();
                // }


                // $(".cart-item-list").detach();
            }else{
                alert("error");
            }
            },
        error:function () {
            alert("失败");
        }
    });

        //添加数量
        // function addNum(){
        //     $.ajax({
        //         url:"/user/addCartItemNum",
        //         dataType:"json",
        //         type:"post",
        //         data: {
        //             userId:3,
        //             productId: 3,
        //         },
        //         success:function (result) {
        //
        //         }
        //     })
        // }
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