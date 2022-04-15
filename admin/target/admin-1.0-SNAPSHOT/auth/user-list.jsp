<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp" %>


<div id="user-list" style="height: 100%">

    <el-form ref="form" :model="form" :inline="true" label-width="70px" style="padding-top: 10px">
        <el-form-item label="用户名">
            <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="用户角色">
            <el-select v-if="serverData.roleList.length" v-model="form.roleId" placeholder="请选择用户角色">
                <el-option v-for="(item, index) in serverData.roleList" :key="index" :label="item.roleName" :value="item.id"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="注册日期">
            <el-col :span="11">
                <el-date-picker type="date" v-model="form.startDate" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col :span="1" style="text-align: center">-</el-col>
            <el-col :span="11">
                <el-date-picker type="date" v-model="form.endDate" style="width: 100%;"></el-date-picker>
            </el-col>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="handleQuery">查询</el-button>
            <el-button type="success" icon="el-icon-circle-plus" @click="toUserAddPage">创建用户</el-button>
        </el-form-item>
    </el-form>

    <el-table v-if="tableHeight" :data="serverData.tableData" size="small" class="box_scorll" border :height="tableHeight">
        <el-table-column label="用户名" width="180">
            <template slot-scope="scope">
                {{ scope.row.userName }}
            </template>
        </el-table-column>
        <el-table-column label="用户编码" width="180">
            <template slot-scope="scope">
                {{ scope.row.userCode }}
            </template>
        </el-table-column>
        <el-table-column label="性别" width="100">
            <template slot-scope="scope">
                {{ scope.row.gender }}
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
                {{ scope.row.userRoleName }}
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

    <el-pagination
            style="margin-top: 20px"
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-sizes="[20, 40, 70, 100]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="serverData.pageTotal">
    </el-pagination>
</div>

<script>
    new Vue({
        el: "#user-list",
        data() {
            return {
                serverData: {
                    roleList: ${roleList},
                    tableData: ${userList},
                    pageTotal: ${userCount}
                },
                form: {
                    username: ${p_username},
                    roleId: ${p_roleId} != 0 ? ${p_roleId} : null,
                    startDate: ${p_startDate} !== null ? new Date(parseInt(${p_startDate})) : null,
                    endDate: ${p_endDate} !== null ? new Date(parseInt(${p_endDate})) : null
                },
                tableHeight: 0,
                pageSize: ${p_pageSize},
                currentPage: ${p_pageNum},
            }
        },
        mounted() {
            this.tableHeightCalc();
            window.onresize = () => this.tableHeightCalc();
        },
        methods: {
            // 动态计算高度
            tableHeightCalc() {
                const reslut = () => this.$el && this.$el.offsetHeight ? this.$el.offsetHeight - 72 - 60 : 200;
                this.tableHeight = reslut();
            },
            // 查询
            handleQuery() {
                let validate = true;
                // 验证时间
                if (this.form.startDate || this.form.endDate) {
                    if (this.form.startDate && this.form.endDate) {
                        if (this.form.startDate > this.form.endDate) {
                            validate = false;
                            this.$message({ type: "warning", message: "开始时间不能大于结束时间!" });
                        } else {
                            this.form.startDate = this.form.startDate.getTime();
                            this.form.endDate = this.form.endDate.getTime();
                        }
                    } else {
                        validate = false;
                        this.$message({ type: "warning", message: "请选择完整的时间范围!" });
                    }
                }
                if (this.form.roleId == null) this.form.roleId = 0;
                if (!validate) return;
                const sendParam = {...this.form, pageSize: this.pageSize, pageNum: this.currentPage};
                location.href = "${pageContext.request.contextPath}" + "/userList.do?" + serializeJson(sendParam);
            },
            // 编辑
            handleEdit(index, row) {
                console.log(index, row);
            },
            // 删除
            handleDelete(index, row) {
                console.log(index, row);
                this.$notify({ title: '提示', message: '删除成功', type: 'success' });
            },
            handleSizeChange(val) {
                this.pageSize = val;
                console.log(val);
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                console.log(val);
            },
            toUserAddPage() {
                location.href = '${pageContext.request.contextPath}/auth/user-add.jsp';
            }
        }
    })
</script>
<%@ include file="../common/footer.jsp" %>
