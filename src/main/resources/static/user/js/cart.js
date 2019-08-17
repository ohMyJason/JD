($(function () {
        show();

        //添加数量
        $("#add-btn").click(function () {
            $.ajax({
                url:"/user/addCartItemNum",
                dataType:"json",
                type:"post",
                data: {
                    userId:3,
                    productId: $(this).parent().next().html()
                },
                success:function (result) {
                    if(result.code == -100){
                        alert("出现意外");
                        window.location.reload();
                    }
                },error:function () {
                    alert("error");
                    window.location.reload();
                }
            });
            var num = $(this).prev().val();
            $(this).prev().val(parseInt(num)+1);
            //计算小计
            var singlePrice = parseInt($(this).parent().parent().find("p").html());
            var little = $(this).parent().parent().find("b");
            little.html((parseInt(num)+1)*singlePrice);
            totalPrice();
            countNum()
        });

        //减少数量
        $("#sub-btn").click(function () {
            $.ajax({
                url:"/user/addCartItemNum",
                dataType:"json",
                type:"post",
                data:{
                    userId:3,
                    productId: $(this).parent().next().html()
                },
                success:function (result) {
                    if(result.code == -100){
                        alert("出现意外");
                        window.location.reload();
                    }
                },error:function () {
                    alert("error");
                    window.location.reload();
                }
            });
            var num = parseInt($(this).next().val());
            if(num>1){
                $(this).next().val(num-1);
            }else{
                $(this).next().val(1);
            }
            //计算小计
            var singlePrice = parseInt($(this).parent().parent().find("p").html());
            var little = $(this).parent().parent().find("b");
            little.html((parseInt(num)-1)*singlePrice);
            totalPrice();
            countNum()
        });

        //全选
        $("input[name='toggle-checkboxes']").click(function () {
            if($(this).is(":checked")){
                $(".list-checkbox").prop("checked",true);
            }else{
                $(".list-checkbox").prop("checked",false);
            }
            totalPrice();
            countNum()
        });

        //删除
        $(".delete").click(function () {
            $.ajax({
                url:"/user/addCartItemNum",
                dataType:"json",
                type:"post",
                data:{
                    userId:3,
                    cartItemId: $(this).parent().prev().html()
                },
                success:function (result) {
                    if(result.code == -100){
                        alert("出现意外");
                        window.location.reload();
                    }
                },error:function () {
                    alert("error");
                    window.location.reload();
                }
            });
            if(window.confirm("是否删除该产品？"))
                $(this).parents(".cart-item-list").remove();
        });

        //计算总价
        function totalPrice() {
            var price = 0;
            $(".cart-item-list").each(function () {
                if($(this).find("input[name = 'choose']").is(':checked')){
                    var num = $(this).children().eq(2).children().eq(3).html();
                    var priceA =  $(this).children().eq(2).children().eq(4).children().eq(1).val();
                    var priceB = parseFloat(num) * parseFloat(priceA);
                    price += priceB;
                }
                $("#totalPrice").html(price);
            })
        }

        //计算总商品数量
        function countNum(){
            var pronum =0;
            $(".cart-item-list").each(function () {
                var num =$(this).children().eq(2).children().eq(4).children().eq(1).val();
                if( $(this).children().eq(1).is(':checked') ){
                    pronum += parseInt(num);
                }
            })
            $("#selected-pro-num").val(pronum);
        }

        //已选中几件商品
        $("input[name = 'choose']").click(function () {
            // var num = $(this).next().children().eq(4).children().eq(1).val();
            // if( $(this).is(':checked') ){
            //     var pronum = parseInt(num) + parseInt($("#selected-pro-num").val());
            // }
            // else {
            //     var pronum = parseInt($("#selected-pro-num").val())- parseInt(num);
            // }
            // $("#selected-pro-num").val(pronum);
            countNum();
            totalPrice();
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
                            $temp.eq(6).html(result.data[i].productPrice * result.data[i].num);
                            $temp.eq(7).html(result.data[i].cartItemId);
                            $("#jd-cart").append($node);
                            $node = $("#jd-cart").children().eq(1).clone(true);
                        }
                    }else {
                        window.location.href="/user/login.html";
                    }
                },
                error: function () {
                    alert("失败");
                }
            })
        }
})
)

