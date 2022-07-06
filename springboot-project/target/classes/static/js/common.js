/**
 * 头部退出登录
 */
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
                    location.href = "@{/logout}";
                })
                .catch(error => { console.log(error) });
        }
    }
});

// 左侧菜单
new Vue({
    el: "#menu",
    data() {
        return {
            isCollapse: true,
            menuList: {
                "0": "home.jsp",
                "1": ["user-list.jsp", "userList.do?method=query", "user-add.jsp"],
                "2": "change-password.jsp",
                "3-0-0": ""
            }
        };
    },
    computed: {
        // 找出当前页的key，用于高亮菜单
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
        // 根据菜单点击跳转页面
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