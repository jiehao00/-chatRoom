<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/23
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="/js/images.js"></script>
   <link type="text/css" rel="stylesheet" href="/css/images.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/user/images.do" method="post" enctype="multipart/form-data">
    <div>
        <p id="ss">头像：</p>
        <div class="item">
            <img class="addImg" onclick="clickImg(this);" src="images/addImg.png" />
            <input name="url" type="file" class="upload_input" onchange="change(this)"/>
            <div class="preBlock">
                <img class="preview" id="preview" alt="" name="imagesUrl" width="50" height="50" />
            </div>
            <img class="delete" onclick="deleteImg(this);" src="images/delete.png"/>
        </div>
    </div>
    <div>
        姓名：<input type="text" name="name"><br>
        密码：<input type="password" name="password">
    </div>
    <input type="submit" value="注册"/>
</form>
</body>
</html>