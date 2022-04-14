<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>中后台管理系统</title>
    <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" />
    <link type="text/css" rel="stylesheet" href="../css/element-ui-2.15.6.min.css?a=1">
    <link type="text/css" rel="stylesheet" href="../css/default.css">
    <link type="text/css" rel="stylesheet" href="../css/public.css?a=2">
    <script type="text/javascript" src="../js/vue.js"></script>
    <script type="text/javascript" src="../js/element-ui-2.15.6.min.js"></script>
</head>
<body>
    <%--header--%>
    <header id="header">
        <div class="left">
            <img src="../img/logo.webp" alt="">
            <span>中后台管理系统</span>
        </div>
        <div class="right">
            <span>${userSession.userName}，欢迎您！</span>
            <el-tooltip class="item" effect="dark" content="退出" placement="bottom">
                <el-button class="out_btn">
                    <a class="log-out" @click="confirmLogout">
                        <i class="el-icon-switch-button"></i>
                    </a>
                </el-button>
            </el-tooltip>
        </div>
    </header>
    <script>
        new Vue({
            el: "#header",
            methods: {
                confirmLogout() {
                    this.$confirm("确定退出登录？", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning",
                        center: true
                    })
                        .then(() => {
                        location.href = "${pageContext.request.contextPath}/logout.do";
                    })
                        .catch(error => { console.log(error) });
                }
            }
        });
    </script>
    <%--主体面板--%>
    <main>
        <%--左侧菜单--%>
        <div class="menu_box">
            <div id="menu">
                <el-menu :default-active="menuDefaultActive"
                         class="el-menu-vertical-demo"
                         @open="handleOpen"
                         @close="handleClose"
                         :collapse="false"
                         @select="handlePage">
                    <el-menu-item index="0">
                        <i class="el-icon-s-home"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>
                    <el-menu-item index="1">
                        <i class="el-icon-user-solid"></i>
                        <span slot="title">用户管理</span>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <i class="el-icon-s-check"></i>
                        <span slot="title">密码修改</span>
                    </el-menu-item>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span slot="title">导航一</span>
                        </template>
                        <el-submenu index="3-0">
                            <span slot="title">选项4</span>
                            <el-menu-item index="3-0-0">选项1</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                    <%--<el-menu-item index="4" disabled>
                        <i class="el-icon-document"></i>
                        <span slot="title">导航三</span>
                    </el-menu-item>
                    <el-menu-item index="5">
                        <i class="el-icon-setting"></i>
                        <span slot="title">导航四</span>
                    </el-menu-item>--%>
                </el-menu>
            </div>
        </div>
        <script>
            new Vue({
                el: "#menu",
                data() {
                    return {
                        isCollapse: true,
                        menuList: {
                            "0": "home.jsp",
                            "1": ["user-list.jsp", "userList.do"],
                            "2": "change-password.jsp",
                            "3-0-0": ""
                        }
                    };
                },
                computed: {
                    <%--找出当前页的key，用于高亮菜单--%>
                    menuDefaultActive () {
                        let back = "0";
                        Object.values(this.menuList).forEach((value, index) => {
                            const urlArr = location.pathname.split("/");
                            if (urlArr[urlArr.length - 1] === value || value.includes(urlArr[urlArr.length - 1])) {
                                back = Object.keys(this.menuList)[index];
                                return;
                            }
                        });
                        return back;
                    }
                },
        methods: {
            <%--根据菜单点击跳转页面--%>
            handlePage(index) {
                if (Object.prototype.toString.call(index) === "[object String]" && this.menuList[index]) {
                    let _url = "${pageContext.request.contextPath}";
                    if (Object.prototype.toString.call(this.menuList[index]) === "[object Array]") {
                        _url += "/" + this.menuList[index][1];
                    } else {
                        _url += "/auth/" + this.menuList[index];
                    }
                    location.href = _url;
                }
            },
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    });
</script>
<%--右侧内容面板--%>
        <aside>