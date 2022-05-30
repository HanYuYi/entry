<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>对象参数</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/testParamForObject" method="post">
用户名：<input name="name"><br>
密码：<input name="password"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
