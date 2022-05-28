<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>request</title>
</head>
<body>
${requestSuccess != null ? "提交成功" : ""}<br>
${restfulCount}<br>
${paramsAccept}<br>
${paramsUsername}<br>
${forwardMsg}
</body>
</html>
