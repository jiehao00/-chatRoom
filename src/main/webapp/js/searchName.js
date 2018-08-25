$(document).ready(function () {
    var out=document.getElementById('out').value;
    console.log(out);
    var array=new Array();
    $.ajax({
        type:'post',
        url:'/user/seachName.do',
        dataType:'json',
        success:function (data) {
            console.log(data)
            for(var i=0;i<data.length;i++){
                console.log(data[i].name)
                if (out!=data[i].name){
                    array.push(data[i].name);
                }
            }
            console.log(array);
            for (var i = 0; i < array.length; i++) {
                //先创建好select里面的option元素
                var option = document.createElement("option");
                //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                $(option).val(array[i]);
                //给option的text赋值,这就是你点开下拉框能够看到的东西
                $(option).text(array[i]);
                //获取select 下拉框对象,并将option添加进select
                $('#serverId').append(option);
            }
        }
    })


})