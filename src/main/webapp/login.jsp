<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/21
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/js/seachImages.js"></script>
<body>
<div>
<h2>用户登录</h2>
<div style="width: 50px; height:50px;border-radius:100%; overflow:hidden;">
    <img src="/upload/images/1535078181582.png" id="image"  style="width: 50px; height:50px">
</div>
<form action="${pageContext.request.contextPath }/user/login.do" method="post">
    userName:<input type="text" name="name" value="${users.name }" id="userName" onchange="seachImages()"/><br/>
    password:<input type="password" name="password" value="${users.password }"/><br/>
    <input type="submit" value="login"/><font color="red">${errorMsg }</font>
</form>
    未注册，点击<a href="/register.jsp">注册</a>
</div>


<%--<img src="/upload/images/1535016904333.png" style="width: 50px; height:50px">--%>

</body>
</html>
