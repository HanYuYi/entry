<%--
  Created by IntelliJ IDEA.
  User: yuyihan
  Date: 2022/4/6
  Time: 10:16 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<p>${error}</p>
<form action="/admin_war_exploded/login" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
