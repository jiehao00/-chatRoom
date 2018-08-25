<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/23
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/images.do" method="post" enctype="multipart/form-data">
    <input type="file" name="url" >
    <input type="submit" value="提交"/>
</form>
</body>
</html>
