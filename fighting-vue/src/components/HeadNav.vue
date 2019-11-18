<template>
    <div>
        <header class="head-nav">
            <el-row>
                <el-col :span="6" class="logo-container">
                    <img src="../assets/images/colored-squares.png" class="logo" alt @click="$router.push('/')"/>
                    <span class="title" @click="goIndex()">Fighting后台管理系统</span>
                </el-col>
                <el-col :span="6" class="user">
                    <div class="userinfo">
                        <img :src="user.avatar" class="avatar" alt/>
                        <div class="welcome">
                            <p class="name comename">欢迎</p>
                            <p class="name avatarname">{{user.nickname}}</p>
                        </div>
                        <span class="username">
                        <el-dropdown trigger="click" @command="setDialogInfo">
                            <span class="el-dropdown-link">
                                <i class="el-icon-caret-bottom el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown" style="width: 150px;">
                                <el-dropdown-item command="info">个人信息</el-dropdown-item>
                                <el-dropdown-item command="changePassword">修改密码</el-dropdown-item>
                                <el-dropdown-item command="logout">退出</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </span>
                    </div>
                </el-col>
            </el-row>
        </header>
        <!--新增修改弹出框-->
        <div>
            <el-dialog
                    title="修改密码"
                    top="30vh"
                    width="20%"
                    customClass="dialogCustomWidth"
                    :visible.sync="dialog.show"
                    :close-on-click-modal="false"
                    :modal-append-to-body="false"
            >
                <el-form :inline="true" ref="dataForm" :model="dataForm" :rules="dataRule" label-width="80px"
                         @submit.native.prevent>
                    <el-form-item prop="oldPassword" label="旧密码">
                        <el-input v-model="dataForm.oldPassword" placeholder="旧密码" show-password clearable></el-input>
                    </el-form-item>
                    <el-form-item prop="password" label="密码">
                        <el-input v-model="dataForm.password" placeholder="密码" show-password clearable></el-input>
                    </el-form-item>
                    <el-form-item prop="password_again" label="再一遍">
                        <el-input v-model="dataForm.password_again" placeholder="密码" show-password clearable></el-input>
                    </el-form-item>
                    <el-form-item style="width: 100%;text-align: center">
                        <el-button type="warning" icon="view" @click="dialog.show = false">取消</el-button>
                        <el-button type="primary" icon="view" @click="handleChange">确定</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import userApi from "@/api/user";
    import common from "@/utils/common";

    export default {
        name: "head-nav",
        computed: {
            user() {
                return this.$store.getters.user;
            }
        },
        data() {
            const validateNewPassword = (rule, value, callback) => {
                if (value === this.dataForm.oldPassword) {
                    callback(new Error('新密码不能与旧密码一致!'));
                } else {
                    callback();
                }
            }
            const validatePasswordAgain = (rule, value, callback) => {
                if (value !== this.dataForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                // dialog部分
                dialog: {
                    show: false
                },
                dataForm: {
                    id: null,
                    oldPassword: '',
                    password: '',
                    password_again: '',
                },
                dataRule: {
                    oldPassword: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    password: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'},
                        {validator: validateNewPassword, trigger: 'change'}
                    ],
                    password_again: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {validator: validatePasswordAgain, trigger: 'change'}
                    ],
                }
            }
        },
        methods: {
            goIndex() {
                common.toIndex();
            },
            setDialogInfo(cmditem) {
                if (!cmditem) {
                    this.$msg.error("菜单选项缺少command属性");
                    return;
                }
                switch (cmditem) {
                    case "info":
                        this.$router.push("/personal/info");
                        break;
                    case "changePassword":
                        this.dataForm = {
                            id: null,
                            oldPassword: '',
                            password: '',
                            password_again: '',
                        };
                        this.dialog.show = true;
                        this.$nextTick(() => {
                            this.$refs['dataForm'].clearValidate();
                        });
                        break;
                    case "logout":
                        this.logout();
                        break;
                }
            },
            handleChange() {
                const valid = common.validateForm(this.$refs['dataForm']);
                // 表单校验
                if (valid) {
                    this.dataForm.id = this.user.id;
                    userApi.changePassword(this.dataForm).then(res => {
                        if (res.data.status == 200) {
                            this.dialog.show = false;
                            this.$alert('密码修改成功，请重新登录', '提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    // 触发退出
                                    this.handleLogout();
                                }
                            });
                        }
                    }).catch(err => {
                    });
                }
            },
            logout: function () {
                this.$confirm("您确定要退出登录吗?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.handleLogout();
                }).catch(() => {
                });
            },
            handleLogout() {
                userApi.logout()
                    .then(res => {
                        common.clearTokenToLogin();
                    }).catch(err => {
                    common.clearTokenToLogin();
                });
            }
        }
    };
</script>

<style scope>
    .head-nav {
        width: 100%;
        height: 50px;
        min-width: 600px;
        padding: 5px;
        background: #324057;
        color: #fff;
        border-bottom: 1px solid #1f2d3d;
    }

    .logo-container {
        line-height: 50px;
        min-width: 360px;
    }

    .logo {
        height: 50px;
        width: 50px;
        margin-right: 5px;
        vertical-align: middle;
        display: inline-block;
        cursor: pointer;
    }

    .title {
        vertical-align: middle;
        font-size: 22px;
        font-family: "Microsoft YaHei";
        /*letter-spacing: 3px;*/
        cursor: pointer;
    }

    .user {
        line-height: 50px;
        text-align: right;
        float: right;
        padding-right: 20px;
        min-width: 180px;
    }

    .avatar {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        vertical-align: middle;
        display: inline-block;
    }

    .welcome {
        display: inline-block;
        width: auto;
        vertical-align: middle;
        padding: 0 10px;
    }

    .name {
        line-height: 20px;
        text-align: center;
        font-size: 14px;
    }

    .comename {
        font-size: 12px;
    }

    .avatarname {
        color: #409eff;
        font-weight: bolder;
    }

    .username {
        cursor: pointer;
        margin-right: 5px;
    }

    .el-dropdown {
        color: #fff;
    }
</style>