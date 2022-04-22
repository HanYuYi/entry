<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>登录中后台管理系统</title>
    <link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />
    <style type="text/css">
        .login_page {
            background-image: linear-gradient(160deg, #ffffff 20%, #92c8ff 80%);
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .login_panel {
            text-align: center;
            width: 500px;
            height: 400px;
            background: #ffffff;
            border-radius: 7px;
            box-shadow: 1px 1px 30px #cbcaca;
            overflow: hidden;
        }
        h2 {
            color: #ffffff;
            font-size: 36px;
            text-shadow: 1px 1px 6px #333;
        }
        p {
            color: #d32d2d;
            height: 22.5px;
        }
        label {
            display: inline-block;
            width: 70px;
            text-align: left;
            color: #333;
        }
        input:not([type="submit"]) {
            width: 270px;
            padding: 0 10px;
            height: 46px;
            border-radius: 5px;
            border: 1px solid #e6e6e6;
        }
        input[type="submit"] {
            width: 270px;
            height: 46px;
            border-radius: 5px;
            border: 1px solid #e6e6e6;
            background: #409eff;
            color: #ffffff;
            font-size: 16px;
            cursor: pointer;
        }
    </style>
</head>
<body class="login_page">
    <div class="login_panel">
        <h2>中后台管理系统</h2>
        <p>${error}</p>
        <form action="${pageContext.request.contextPath}/login.do" method="post">
            <label>用户名：</label><input type="text" name="username"><br><br>
            <label>密码：</label><input type="password" name="password"><br><br>
            <label></label><input type="submit" value="登录">
        </form>
    </div>
</body>
</html>
