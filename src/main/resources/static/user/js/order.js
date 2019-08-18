($(function () {

    show();
        function show() {
            $.ajax({
                url: "http://localhost:8080/user/showItem",
                type: "post",
                traditional:true,
                dataType: "json",
                data: {
                    IdArry: ['1','3','4','5','6']
                },
                success: function (result) {
                    if(result.code == 0){
                        alert("success");
                        // alert(result.data[0]);
                        // alert(result.data[0].businessName);  //商家2
                        // alert(result.data[1].productId);     //6
                        // alert(result.data[1].productName);   //苹果
                        // alert(result.data[1].producePrice);  //20
                        // alert(result.data[1].productImgUrl); //图片
                        // alert(result.data[1].detail1);       //详情null
                        // alert(result.data[1].num);           //数量
                        // alert(result.data[1].cartItemId);    //4
                        var $node = $("#change_box").clone(true);
                        // $("#change_box").detach();
                        for(var i=0; i < 1; i++){
                            $node.children().eq(0).html("店名");
                            var imgsrc = "../img/apple.png";
                            $node.children().eq(1).find("img").attr("src",imgsrc);
                            $node.children().eq(1).find("#dcr_t_left").children("ul").children("li").eq(0).html("苹果");
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(0).html("20元");
                            $node.children().eq(1).find("#dcr_t_num").children().html("x3");
                            $("#deliver_cont_right").append($node);
                            $node = $("#change_box").clone(true);
                        }
                    }else{
                        alert("失败");
                    }
                },error: function () {
                    alert("error");
                }
            })
        }
    })
)