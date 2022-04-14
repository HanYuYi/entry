<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .table_scorll {
        height: calc(100% - 62px - 10px - 50px);
        overflow: auto!important;
    }
</style>
<%@ include file="../common/header.jsp" %>


<div id="user-list" style="height: 100%">

    <el-form ref="form" :model="form" :inline="true" label-width="70px" style="padding-top: 10px">
        <el-form-item label="用户名">
            <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="用户角色">
            <el-select v-if="serverData.roleList.length" v-model="form.role" placeholder="请选择用户角色">
                <el-option v-for="(item, index) in serverData.roleList" :key="index" :label="item.roleName" :value="item.id"></el-option>
            </el-select>
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
            <el-button type="primary" icon="el-icon-search">查询</el-button>
            <el-button type="success" icon="el-icon-circle-plus">添加用户</el-button>
        </el-form-item>
    </el-form>

    <el-table v-if="tableHeight" :data="tableData" size="small" class="table_scorll" border :height="tableHeight">
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
                <el-popconfirm title="确定删除该用户？"icon="el-icon-info" @confirm="handleDelete(scope.$index, scope.row)">
                    <el-button slot="reference" size="mini" type="danger">删除</el-button>
                </el-popconfirm>
            </template>
        </el-table-column>
    </el-table>
</div>

<script>
    new Vue({
        el: "#user-list",
        data() {
            return {
                serverData: {
                    roleList: []
                },
                form: {
                    username: "",
                    role: "",
                    start: "",
                    end: ""
                },
                tableHeight: 0,
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
        mounted() {
            this.tableHeightCalc();
            window.onresize = () => this.tableHeightCalc();

            if (${roleList}) {
                this.serverData.roleList = ${roleList};
                console.log(${roleList})
            }
        },
        methods: {
            tableHeightCalc() {
                const reslut = () => this.$el && this.$el.offsetHeight ? this.$el.offsetHeight - 72 - 40 : 200;
                this.tableHeight = reslut();
            },
            handleEdit(index, row) {
                console.log(index, row);
            },
            handleDelete(index, row) {
                console.log(index, row);
                this.$message({
                    type: "success",
                    message: "删除成功!"
                });
            }
        }
    })
</script>
<%@ include file="../common/footer.jsp" %>
