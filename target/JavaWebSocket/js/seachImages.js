function seachImages() {
var name=document.getElementById("userName").value;
console.log(name);
$.ajax({
    type:'post',
    url:'/user/seachImage.do?userName='+name,
    // data:{userName:name},
    dataType:'json',
    success:function (data) {
        console.log(data);
        $("#image").attr("src",data);
    }
})
}