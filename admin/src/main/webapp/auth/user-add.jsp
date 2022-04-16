<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../common/header.jsp" %>

<div id="user-add">
    <div style="margin-top: 20px;text-align: right;margin-right: 20px">
        <el-button type="primary" plain icon="el-icon-back" circle @click="toUserListPage" ></el-button>
    </div>
    <div class="box_scorll" style="width: 500px">
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="用户名" prop="username"
                          :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]">
                <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender"
                          :rules="[{ required: true, message: '请选择性别', trigger: 'change' }]">
                <el-select v-model="form.gender" placeholder="请选择性别" style="width: 100%;">
                    <el-option label="男" value="1"></el-option>
                    <el-option label="女" value="0"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="生日" prop="birthday"
                          :rules="[{ type: 'date', required: true, message: '请选择生日日期', trigger: 'change' }]">
                <el-date-picker type="date" v-model="form.birthday" style="width: 100%;"></el-date-picker>
            </el-form-item>
            <el-form-item label="用户角色" prop="userRole"
                          :rules="[{ required: true, message: '请选择用户角色', trigger: 'change' }]">
                <el-select v-model="form.userRole" placeholder="请选择用户角色" style="width: 100%;">
                    <el-option label="男" value="1"></el-option>
                    <el-option label="女" value="0"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="联系方式" prop="phone"
                          :rules="[{ required: true, message: '请输入联系方式', trigger: 'blur' }]">
                <el-input v-model="form.phone"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address"
                          :rules="[{ required: true, message: '请输入地址', trigger: 'blur' }]">
                <el-input v-model="form.address"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="resetForm">重置</el-button>
                <el-button type="primary" @click="submitForm">立即创建</el-button>
            </el-form-item>
        </el-form>
    </div>
</div>

<script>
    new Vue({
        el: "#user-add",
        data() {
            return {
                form: {
                    username: "",
                    gender: "",
                    birthday: null,
                    userRole: null,
                    phone: "",
                    address: ""
                }
            }
        },
        methods: {
            submitForm () {
                this.$refs.form.validate(valid => {
                    if (valid) {}
                });
            },
            resetForm () {
                this.$refs.form.resetFields();
            },
            toUserListPage() {
                location.href = '${pageContext.request.contextPath}/userList.do';
            }
        }
    })
</script>
<%@ include file="../common/footer.jsp" %>
