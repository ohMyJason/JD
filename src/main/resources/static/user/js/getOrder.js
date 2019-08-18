($(function () {
    $.ajax({
        url:"http://localhost:8080/user/getAllOrder",
        type:"post",
        dataType:"json",
        data:{
            userId:3
        },
        success:function (result){
            console.log((result.data));
            if(result.code == 0){

            }

        }
    })
}))