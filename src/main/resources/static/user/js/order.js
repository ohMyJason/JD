($(function () {
    var str = [1,3,4,5,6];
    $.ajax({
        url:"/user/showItem",
        type: "post",
        dataType: "json",
        // headers:{'token':$.cookie("token")},
        data: {
            IdArry:str
            // userId: $.cookie('userId')
        },
        success:function (result) {
            if(result.code == 0){
                alert(1);
                // var $node = $("#dcr_cont").clone(true);
                // $("#dcr_cont").detach();
                // for(var i = 0; i < result.count; i++){
                //     $node.prev().html(result.data[i].businessName);
                //     var imgsrc = ".." + result.data[i].productImgUrl;
                //     $node.eq(0).children().attr("src",imgsrc);
                //     $node.eq(1).children().eq(0).eq(0).html(result.data[i].productName);
                //     // $node.eq(1).children().eq(0).eq(1).html(result.data[i].);
                //     $node.eq(1).eq(1).eq(0).html(result.data[i].productPrice);
                //     $node.eq(1).eq(2).children().html(result.data[i].num);
                //     $("#deliver_cont_right").append($node);
                //     $node = $("#dcr_cont").clone(true);
                // }
            }else {
                alert("error");
                // window.location.href="/user/login.html";
            }
        },
        error: function () {
            alert("失败");
        }
    })

        //几件商品
        // $("#productNum").html();
})
)