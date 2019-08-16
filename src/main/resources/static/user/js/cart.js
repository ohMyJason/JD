($(function () {
        show();

        $("#add-btn").click(function () {
            alert(1);
            // alert(this);
            // $.ajax({
            //     url:"/user/addCartItemNum",
            //     dataType:"json",
            //     type:"post",
            //     data: {
            //         userId:3,
            //         productId: this.parent().next().html()
            //     },
            //     success:function (result) {
            //         alert('success');
            //     }
            // })
        });

        //减少数量
        $("#sub-btn").click(function () {
            var num = parseInt($("#number").html());
            if(num>1){
                $("#number").val(num-1);
            }else{
                $("#number").val(1);
            }
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
                            $node = $("#jd-cart").children().eq(1).clone();
                        }
                    }
                },
                error: function () {
                    alert("失败");
                }
            })
        }


})
)

