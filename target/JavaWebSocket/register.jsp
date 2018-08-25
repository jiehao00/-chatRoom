<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/22
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/js/images.js"></script>
<link type="text/css" rel="stylesheet" href="/css/images.css">
<body>
<h2>用户注册</h2>
<form action="${pageContext.request.contextPath }/user/register.do" method="post" enctype="multipart/form-data">
    <div>
        <p id="ss">头像：</p>
        <div class="item">
            <img class="addImg" onclick="clickImg(this);" src="${pageContext.request.contextPath}/images/addImg.png" />
            <input name="url" type="file" class="upload_input" onchange="change(this)"/>
            <div class="preBlock">
                <img class="preview" id="preview" alt="" name="imagesUrl" width="50" height="50" />
            </div>
            <img class="delete" onclick="deleteImg(this);" src="${pageContext.request.contextPath}/images/delete.png"/>
        </div>
    </div>
    userName:<input type="text" name="name" /><br/>
    password:<input type="password" name="password"/><br/>
    <input type="submit" value="注册"/><font color="red">${errorMsg}</font>
</form>
返回<a href="/login.jsp">登录</a>
</div>
</body>
</html>
