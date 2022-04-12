<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../common/header.jsp" %>

<div id="user-list">

    <el-form ref="form" :model="form" :inline="true" label-width="70px" style="margin-top: 10px">
        <el-form-item label="用户名">
            <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="注册日期">
            <el-col :span="11">
                <el-date-picker type="date" v-model="form.start" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col :span="1" style="text-align: center">-</el-col>
            <el-col :span="11">
                <el-date-picker type="date" v-model="form.end" style="width: 100%;"></el-date-picker>
            </el-col>
        </el-form-item>
        <el-form-item>
            <el-button type="primary">查询</el-button>
        </el-form-item>
    </el-form>

    <el-table :data="tableData" style="width: 100%" border>
        <el-table-column label="用户名" width="180">
            <template slot-scope="scope">
                {{ scope.row.username }}
            </template>
        </el-table-column>
        <el-table-column label="用户编码" width="180">
            <template slot-scope="scope">
                {{ scope.row.userCode }}
            </template>
        </el-table-column>
        <el-table-column label="性别" width="100">
            <template slot-scope="scope">
                {{ scope.row.sex }}
            </template>
        </el-table-column>
        <el-table-column label="生日" width="120">
            <template slot-scope="scope">
                {{ scope.row.birthday }}
            </template>
        </el-table-column>
        <el-table-column label="年龄" width="100">
            <template slot-scope="scope">
                {{ scope.row.age }}
            </template>
        </el-table-column>
        <el-table-column label="角色" width="120">
            <template slot-scope="scope">
                {{ scope.row.role }}
            </template>
        </el-table-column>
        <el-table-column label="注册日期" width="200">
            <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.createDate }}</span>
            </template>
        </el-table-column>
        <el-table-column label="更多" width="100">
            <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                    <p>电话: {{ scope.row.phone }}</p>
                    <p>地址: {{ scope.row.address }}</p>
                    <div slot="reference">
                        <el-tag size="medium">...</el-tag>
                    </div>
                </el-popover>
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button size="mini"
                        @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="mini" type="danger"
                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
</div>

<script>
    new Vue({
        el: "#user-list",
        data() {
            return {
                form: {
                    username: "",
                    start: "",
                    end: ""
                },
                tableData: [{
                    username: "王锋",
                    userCode: 1,
                    sex: "男",
                    birthday: "11-15",
                    age: 31,
                    phone: "13551882174",
                    address: "上海市普陀区金沙江路 1518 弄",
                    role: "运营",
                    createDate: "2016-05-02",
                }, {
                    username: "何晓卫",
                    userCode: 2,
                    sex: "女",
                    birthday: "08-08",
                    age: 22,
                    phone: "17688410258",
                    address: "上海市普陀区金沙江路 1517 弄",
                    role: "管理员",
                    createDate: "2016-05-04",
                }]
            }
        },
        methods: {
            handleEdit(index, row) {
                console.log(index, row);
            },
            handleDelete(index, row) {
                this.$confirm("确定删除该用户？", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.$message({
                        type: "success",
                        message: "删除成功!"
                    });
                })
                console.log(index, row);
            }
        }
    })
</script>
<%@ include file="../common/footer.jsp" %>
