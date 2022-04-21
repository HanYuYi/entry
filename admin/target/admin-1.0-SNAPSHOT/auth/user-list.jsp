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

    <el-table v-if="tableHeight"
              :data="serverData.tableData"
              size="small"
              @sort-change="handleSort"
              class="box_scorll"
              border
              :height="tableHeight"
              :default-sort="sortRow">
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
                {{ scope.row.birthdayFmt }}
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
        <el-table-column prop="createDateFmt" label="注册日期" width="200" sortable>
            <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.createDateFmt }}</span>
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
        <el-table-column prop="modifyDateFmt"  label="上次更新日期" width="200" sortable>
            <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.modifyDateFmt }}</span>
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button size="mini"
                        @click="openEdit(scope.$index, scope.row)">编辑</el-button>
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
            :page-sizes="[10, 20, 40, 70]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="serverData.pageTotal">
    </el-pagination>

    <%--编辑弹窗--%>
    <el-dialog title="编辑用户信息" :visible.sync="dialogFormVisible" width="700px" custom-class="pad_dialog" center append-to-body>
        <el-form ref="updateFormRef" :model="updateForm" :rules="updateFormRules" label-width="80px">
            <el-form-item label="用户名" prop="userName">
                <el-input v-model="updateForm.userName"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-select v-model="updateForm.gender" style="width: 100%;">
                    <el-option label="男" :value="1"></el-option>
                    <el-option label="女" :value="0"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="生日" prop="birthday">
                <el-date-picker type="date" v-model="updateForm.birthday" style="width: 100%;"></el-date-picker>
            </el-form-item>
            <el-form-item label="用户角色" prop="userRole">
                <el-select v-if="serverData.roleList.length" :disabled="disabledUpdateUserRole" v-model="updateForm.userRole" style="width: 100%;">
                    <el-option v-for="(item, index) in updateFormRoleList" :key="index" :label="item.roleName" :value="item.id"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="联系方式" prop="phone">
                <el-input v-model="updateForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
                <el-input v-model="updateForm.address"></el-input>
            </el-form-item>
            <el-form-item label=" ">
                <p style="color: #d32d2d">${userUpdateError}</p>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleEdit">确 定</el-button>
        </div>
    </el-dialog>
</div>

<script>
    new Vue({
        el: "#user-list",
        data() {
            const validatePhone = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error('请输入联系方式'));
                } else {
                    if (!(/^[1]\d{10}$/.test(value))) {
                        callback(new Error('你的手机号码格式有误'));
                    }
                    callback();
                }
            };
            return {
                serverData: {
                    roleList: ${roleList},
                    tableData: ${userList},
                    pageTotal: ${userCount}
                },
                sortRow: { prop: "${p_sortColumn}", order: "${_order}" == "1" ? "ascending" : "descending" },
                form: {
                    username: "${p_username}",
                    roleId: ${p_roleId} != 0 ? ${p_roleId} : null,
                    startDate: "${p_startDate}" !== "" ? new Date(parseInt(${p_startDate})) : null,
                    endDate: "${p_endDate}" !== "" ? new Date(parseInt(${p_endDate})) : null,
                    sortColumn: "createDateFmt",
                    order: 1
                },
                tableHeight: 0,
                dialogFormVisible: false,
                pageSize: ${p_pageSize},
                currentPage: ${p_pageNum},
                currentLevel: ${userSession.userRole},
                beforeUpdateRowUserRole: null,
                updateFormRoleList: [],
                updateForm: {
                    id: null,
                    userName: "",
                    gender: 1,
                    birthday: null,
                    userRole: null,
                    phone: "",
                    address: ""
                },
                updateFormRules: {
                    userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                    gender: [{ required: true, message: '请选择性别', trigger: 'change' }],
                    birthday: [{ type: 'date', required: true, message: '请选择生日日期', trigger: 'change' }],
                    userRole: [{ required: true, message: '请选择用户角色', trigger: 'change' }],
                    phone: [
                        { required: true, message: '请输入联系方式', trigger: 'blur' },
                        { validator: validatePhone, trigger: 'blur' }
                    ],
                    address: [{ required: true, message: '请输入地址', trigger: 'blur' }]
                }
            }
        },
        computed: {
            disabledUpdateUserRole() {
                return !(this.currentLevel < Number.parseInt(this.beforeUpdateRowUserRole));
            }
        },
        mounted() {
            this.tableHeightCalc();
            window.onresize = () => this.tableHeightCalc();

            this.shiftUserRole();

            if ("${userUpdateSuccess}") {
                this.$notify({ title: '提示', message: '${userUpdateSuccess}', type: 'success' });
            }

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
                        }
                    } else {
                        validate = false;
                        this.$message({ type: "warning", message: "请选择完整的时间范围!" });
                    }
                }
                if (this.form.roleId == null) this.form.roleId = 0;
                if (!validate) return;
                const sendParam = {
                    ...this.form,
                    startDate: dateFormat("yyyy-MM-dd HH:mm:ss", this.form.startDate),
                    endDate: dateFormat("yyyy-MM-dd HH:mm:ss", this.form.endDate),
                    pageSize: this.pageSize,
                    pageNum: this.currentPage
                };
                location.href = "${pageContext.request.contextPath}" + "/userList.do?method=query&" + serializeJson(sendParam);
            },
            // 开启编辑
            openEdit(index, row) {
                this.beforeUpdateRowUserRole = row.userRole;
                const { id, userName, gender, birthday, userRole, phone, address } = row;
                this.updateForm = {
                    id,
                    userName,
                    gender: gender === "男" ? 1 : 0,
                    birthday: new Date(birthday),
                    userRole,
                    phone,
                    address
                };
                console.log(this.updateForm);
                this.dialogFormVisible = true;
            },
            // 提交编辑
            handleEdit() {
                this.$refs.updateFormRef.validate(valid => {
                    if (valid) {
                        const birthdayStr = dateFormat("yyyy-MM-dd HH:mm:ss", this.updateForm.birthday);
                        const sendUpdateForm = { ...this.updateForm, method: "update", birthday: birthdayStr };
                        ajax({
                            url: "${pageContext.request.contextPath}/userList.do",
                            type: "get",
                            data: sendUpdateForm,
                        }).then(({status, message}) => {
                            if (status === 1) {
                                this.dialogFormVisible = false;
                                this.handleQuery();
                            } else {
                                this.$notify.error({ title: '提示', message });
                            }
                        })
                    }
                });
            },
            // 将角色信息第一条的全部删除
            shiftUserRole() {
                const newRoleList = [...this.serverData.roleList];
                newRoleList.shift();
                this.updateFormRoleList = newRoleList;
            },
            // 删除
            handleDelete(index, row) {
                const sendUpdateForm = { method: "delete", id: row.id };
                ajax({
                    url: "${pageContext.request.contextPath}/userList.do",
                    type: "get",
                    data: sendUpdateForm,
                }).then(({status, message}) => {
                    if (status === 1) {
                        this.handleQuery();
                    } else {
                        this.$notify.error({ title: '提示', message });
                    }
                })
            },
            // 排序
            handleSort({ column, prop, order }) {
                this.form.sortColumn = prop;
                this.form.order = order === "ascending" ? 1 : 0;
                this.handleQuery();
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.handleQuery();
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.handleQuery();
            },
            toUserAddPage() {
                location.href = '${pageContext.request.contextPath}/auth/user-add.jsp';
            }
        }
    })
</script>
<%@ include file="../common/footer.jsp" %>
