($(function () {
        show();

        $("#add-btn").click(function () {
            $.ajax({
                url:"/user/addCartItemNum",
                dataType:"json",
                type:"post",
                data: {
                    userId:1000,
                    productId: $(this).parent().next().html()
                },
                success:function (result) {
                    if(result.code == -100){
                        alert("出现意外");
                        window.location.reload();
                    }
                },error:function () {
                    alert("出现意外");
                    window.location.reload();
                }
            })
            var num = $(this).prev().val();
                $(this).prev().val(parseInt(num)+1);
        });

        function show() {
            $.ajax({
                url: "http://localhost:8080/user/showCartItem",
                type: "post",
                dataType: "json",
                data: {
                    userId: 3
                },
                success: function (result) {
                    if(result.code == 0) {
                        var $node = $(".cart-item-list").clone(true);
                        $(".cart-item-list").detach();
                        for (var i = 0; i < result.count; i++) {
                            // var $node = $(".cart-item-list").detach();
                            $node.children().eq(0).find("a").children().html(result.data[i].businessName);
                            var imgsrc = ".." + result.data[i].productImgUrl;
                            var $temp = $node.children().eq(2).children();
                            $temp.eq(0).attr("src", imgsrc);
                            $temp.eq(1).html(result.data[i].productName);
                            $temp.eq(2).html(result.data[i].detail1);
                            $temp.eq(3).html(result.data[i].productPrice);
                            $temp.eq(4).children().eq(1).val(result.data[i].num);
                            $temp.eq(5).html(result.data[i].productId);
                            $("#jd-cart").append($node);
                            $node = $("#jd-cart").children().eq(1).clone(true);
                        }
                    }
                },
                error: function () {
                    alert("失败");
                }
            })
        }

        // //添加数量
        // $("#add-btn").click(function () {
        //     var num = $(this).prev().val();
        //     $(this).prev().val(parseInt(num)+1);
        //     $("#number").trigger("input");
        //     // alert($(this).parent().parent().eq(6).text());
        //     // $(".total-price").text(parseInt($("#number").val())*parseInt($(".pro-price").val()));
        // });

        //减少数量
        $("#sub-btn").click(function () {
            var num = parseInt($(this).next().val());
            if(num>1){
                $(this).next().val(num-1);
            }else{
                $(this).next().val(1);
            }
        });

})
)

