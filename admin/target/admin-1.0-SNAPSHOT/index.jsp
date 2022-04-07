<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>首页</title>
    <link type="text/css" rel="stylesheet" href="./css/element-ui-2.15.6.min.css">
    <link type="text/css" rel="stylesheet" href="./css/default.css">
    <link type="text/css" rel="stylesheet" href="./css/public.css">
    <script type="text/javascript" src="./js/vue.js"></script>
    <script type="text/javascript" src="./js/element-ui-2.15.6.min.js"></script>
</head>
<body>
    <%@ include file="common/main-start.jsp" %>

    <div id="home">
    </div>

    <%@ include file="common/main-end.jsp" %>

<script>
    new Vue({ el: "#home" });
</script>
</body>
</html>
