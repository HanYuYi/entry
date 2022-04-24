<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../common/header.jsp" %>

<div id="user-add">
    <div>
        <el-button type="primary" plain icon="el-icon-back" circle @click="toUserListPage"></el-button>
    </div>
    <div style="width: 500px;margin: 0 auto">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">

            <el-form-item label="头像">
                <el-upload
                        class="avatar_uploader"
                        action="${pageContext.request.contextPath}/userList.do?method=uploadUserAvatar"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :on-error="handleAvatarError"
                        :before-upload="beforeAvatarUpload">
                    <img v-if="avatarUrl" :src="avatarUrl" class="avatar_upload_img">
                    <i v-else class="el-icon-plus avatar_uploader_icon"></i>
                </el-upload>
            </el-form-item>

            <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="confirmPassword">
                <el-input v-model="form.confirmPassword"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-select v-model="form.gender" placeholder="请选择性别" style="width: 100%;">
                    <el-option label="男" value="1"></el-option>
                    <el-option label="女" value="0"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="生日" prop="birthday">
                <el-date-picker type="date" v-model="form.birthday" style="width: 100%;"></el-date-picker>
            </el-form-item>
            <el-form-item label="用户角色" prop="userRole">
                <el-select v-if="serverData.roleList.length" v-model="form.userRole" placeholder="请选择用户角色" style="width: 100%;">
                    <el-option v-for="(item, index) in updateFormRoleList" :key="index" :label="item.roleName" :value="item.id"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="联系方式" prop="phone">
                <el-input v-model="form.phone"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
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
            const validateConfirmPass = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error('请确认密码'));
                } else {
                    if (this.form.confirmPassword !== this.form.password) {
                        callback(new Error('两次输入的密码不一致'));
                    }
                    callback();
                }
            };
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
                },
                avatarUrl: "",
                form: {
                    avatar: "",
                    username: "",
                    password: "",
                    confirmPassword: "",
                    gender: "",
                    birthday: null,
                    userRole: null,
                    phone: "",
                    address: ""
                },
                rules: {
                    username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                    password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
                    confirmPassword: [
                        { required: true, message: '请确认密码', trigger: 'blur' },
                        { validator: validateConfirmPass, trigger: 'blur' }
                    ],
                    gender: [{ required: true, message: '请选择性别', trigger: 'change' }],
                    birthday: [{ type: 'date', required: true, message: '请选择生日日期', trigger: 'change' }],
                    userRole: [{ required: true, message: '请选择用户角色', trigger: 'change' }],
                    phone: [
                        { required: true, message: '请输入联系方式', trigger: 'blur' },
                        { validator: validatePhone, trigger: 'blur' }
                    ],
                    address: [{ required: true, message: '请输入地址', trigger: 'blur' }]
                },
                updateFormRoleList: [],
            }
        },
        mounted() {
            this.shiftUserRole();
        },
        methods: {
            // 头像上传成功的回调
            handleAvatarSuccess(res, file) {
                this.avatarUrl = URL.createObjectURL(file.raw);
                if (res.status === 1 && res.data) {
                    this.form.avatar = res.data;
                }
            },
            handleAvatarError(err) {
                console.log(err);
            },
            // 头像上传限制
            beforeAvatarUpload(file) {
                const isFormat = file.type === "image/jpeg" || file.type === "image/png";
                const isLt20M = file.size / 1024 / 1024 <= 20;
                if (!isFormat) {
                    this.$message.error("上传头像图片只能是 JPG 或 PNG格式!");
                }
                if (!isLt20M) {
                    this.$message.error("上传头像图片大小不能超过 2MB!");
                }
                return isFormat && isLt20M;
            },
            submitForm () {
                this.$refs.form.validate(valid => {
                    if (valid) {
                        ajax({
                            url: "${pageContext.request.contextPath}/user/createUser",
                            type: "post",
                            data: { ...this.form, birthday: dateFormat("yyyy-MM-dd", this.form.birthday) }
                        }).then(({status, message}) => {
                            if (status === 1) {
                                this.$notify({ title: "提示", message: message, type: "success" });
                                this.resetForm();
                                this.avatarUrl = "";
                            } else {
                                this.$notify.error({ title: "提示", message: message });
                            }
                            console.log(message);
                        })
                    }
                });
            },
            resetForm () {
                this.$refs.form.resetFields();
            },
            toUserListPage() {
                location.href = '${pageContext.request.contextPath}/userList.do?method=query';
            },
            // 将角色信息第一条的全部删除
            shiftUserRole() {
                const newRoleList = [...this.serverData.roleList];
                newRoleList.shift();
                this.updateFormRoleList = newRoleList;
            },
        }
    })
</script>
<%@ include file="../common/footer.jsp" %>
