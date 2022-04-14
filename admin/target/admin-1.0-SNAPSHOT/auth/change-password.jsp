<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="../js/ajax.js?date=20220414"></script>

<%@ include file="../common/header.jsp" %>

<div id="change-password">
    <el-form :model="formData" ref="form" label-width="100px" style="width: 430px;margin: 20px">
        <el-form-item prop="username"
                label="用户名"
                :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]"
        >
            <el-input v-model="formData.username"></el-input>
        </el-form-item>
        <el-form-item prop="password"
                      label="新密码"
                      :rules="[{ required: true, message: '请输入新密码', trigger: 'blur' }]"
        >
            <el-input v-model="formData.password" type="password"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword"
                      label="确认密码"
                      :rules="[{ required: true, message: '请确认新密码', trigger: 'blur' }]"
        >
            <el-input v-model="formData.confirmPassword" type="password"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button @click="resetForm">重置</el-button>
            <el-button type="primary" @click="submitForm">提交</el-button>
        </el-form-item>
    </el-form>
</div>

<script>
    new Vue({
        el: "#change-password",
        data () {
            return {
                formData: {
                    username: "",
                    password: "",
                    confirmPassword: ""
                },
            }
        },
        methods: {
            submitForm () {
                this.$refs.form.validate(valid => {
                    if (valid) {
                        ajax({
                            url: "${pageContext.request.contextPath}" + "/user/passwordChange",
                            type: "post",
                            data: this.formData
                        }).then((resp) => {
                            if (resp.status === 1) {
                                this.resetForm()
                                this.$message({ message: resp.message, type: 'success' });
                            } else {
                                this.$message.error({ message: resp.message });
                            }
                        }).catch(error => {
                            new Error(error);
                        })
                    } else {
                        return;
                    }
                });
            },
            resetForm () {
                this.$refs.form.resetFields();
            }
        }
    });
</script>
<%@ include file="../common/footer.jsp" %>