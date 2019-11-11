<template>
    <div class="register">
        <el-form class="register-container" :model="registerForm" :rules="rules" ref="registerForm">
            <h1 class="register_title">注 册 页</h1>
            <el-form-item label="用户名" prop="username">
                <el-input v-model="registerForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="registerForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button class="register_button" type="success" @click="$router.push('/login')">去 登 录</el-button>
                <el-button
                        class="register_button"
                        type="primary"
                        :disabled="buttonDisabled"
                        @click="submitClick('registerForm')"
                >注 册
                </el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import userApi from "@/api/user";

    export default {
        name: "Register",
        components: {},
        data() {
            return {
                rules: {
                    username: [
                        {required: true, message: "请输入用户名", trigger: "blur"}
                    ],
                    password: [
                        {required: true, message: "请输入密码", trigger: "blur"}
                    ]
                },
                registerForm: {},
                buttonDisabled: false
            };
        },
        methods: {
            submitClick: function (formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.$msg.warning("功能开发中。。。");
                    }
                });
            }
        }
    };
</script>

<style>
    .register {
        min-width: 422px;
        min-height: 680px;
        width: 100%;
        height: 100%;
        background: url(../assets/images/bg.jpg) no-repeat;
        background-size: 100% 100%;
    }

    .register-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 0px auto;
        position: relative;
        top: 20%;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .register_title {
        margin: 0px auto 30px auto;
        text-align: center;
        color: #505458;
    }

    .register_remember {
        margin: 0px 0px 35px 0px;
        text-align: left;
    }

    .register_button {
        width: 48%;
        margin-top: 10px;
    }
</style>