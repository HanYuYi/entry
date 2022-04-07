<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--header--%>
<header id="header">
    <div class="left">
        <img src="img/logo.webp" alt="">
        <span>中后台管理系统</span>
    </div>
    <div class="right">
        <span>${userSession.userName}，欢迎您！</span>
        <el-button type="danger" plain icon="el-icon-switch-button">退出</el-button>
    </div>
</header>
<script>
    new Vue({ el: "#header" });
</script>
<%--主体面板--%>
<main>
    <%--左侧菜单--%>
    <div id="menu">
        <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="false">
            <el-submenu index="1">
                <template slot="title">
                    <i class="el-icon-location"></i>
                    <span slot="title">导航一</span>
                </template>
                <el-submenu index="1-4">
                    <span slot="title">选项4</span>
                    <el-menu-item index="1-4-1">选项1</el-menu-item>
                </el-submenu>
            </el-submenu>
            <el-menu-item index="2">
                <i class="el-icon-menu"></i>
                <span slot="title">导航二</span>
            </el-menu-item>
            <el-menu-item index="3" disabled>
                <i class="el-icon-document"></i>
                <span slot="title">导航三</span>
            </el-menu-item>
            <el-menu-item index="4">
                <i class="el-icon-setting"></i>
                <span slot="title">导航四</span>
            </el-menu-item>
        </el-menu>
    </div>
<script>
    new Vue({
        el: "#menu",
        data() {
            return {
                isCollapse: true
            };
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    });
</script>