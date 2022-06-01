<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>对象参数</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/testParamForObject" method="post">
用户名：<input name="name"><br>
密码：<input name="password"><br>
生日：<input type="date" name="birthday"><br>
爱好：<input type="checkbox" name="hobby[0]">唱歌
    <input type="checkbox" name="hobby[1]">跳舞
    <input type="checkbox" name="hobby[2]">打篮球<br>
    <input type="submit" value="提交">
</form>

<form action="${pageContext.request.contextPath}/testParamForDate" method="get">
    日期：<input type="date" name="date"><br>
    <input type="submit" value="提交">
</form>

<form action="${pageContext.request.contextPath}/testParamForDateConverter" method="get">
    日期：<input type="date" name="date"><br>
    <input type="submit" value="提交">
</form>

<form action="${pageContext.request.contextPath}/testParamForDateConverter" method="get">
    日期：<input type="date" name="date"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
