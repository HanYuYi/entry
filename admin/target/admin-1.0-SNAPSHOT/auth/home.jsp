<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp" %>

    <div id="home">
        <strong style="fontSize: 30px">${userSession.userName}，你好！欢迎登录后台系统！</strong>
    </div>

<script>
    new Vue({ el: "#home" });
</script>
<%@ include file="../common/footer.jsp" %>



