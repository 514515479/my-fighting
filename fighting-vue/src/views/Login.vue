<template>
    <div class="login">
        <el-form class="login-container" :model="loginForm" :rules="rules" ref="loginForm">
            <h1 class="login_title">登 录 页</h1>
            <el-form-item label="用户名" prop="username">
                <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="loginForm.password" placeholder="请输入密码" show-password clearable></el-input>
            </el-form-item>
            <!-- <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox> -->
            <el-form-item>
                <!--<el-button class="login_button" type="success" @click="$router.push('/register');">去 注 册</el-button>-->
                <el-button class="login_button" type="success" @click="$router.push('/');">去 主 页</el-button>
                <el-button
                        class="login_button"
                        type="primary"
                        :disabled="buttonDisabled"
                        @click="submitClick('loginForm')"
                >登 录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
import userApi from "@/api/user";

export default {
    name: "Login",
    components: {},
    created() {
        // 全局监听键盘事件
        let _this = this;
        document.onkeydown = function(e) {
            let key = window.event.keyCode;
            if (key === 13 && _this.buttonDisabled === false) {
                _this.submitClick("loginForm");
            }
        };
    },
    beforeDestroy() {
        // 取消全局监听键盘事件
        document.onkeydown = function(e) {};
    },
    data() {
        return {
            rules: {
                username: [
                    { required: true, message: "请输入用户名", trigger: "blur" }
                ],
                password: [
                    { required: true, message: "请输入密码", trigger: "blur" }
                ]
            },
            checked: true,
            loginForm: {
                username: "guest",
                password: "guest"
            },
            buttonDisabled: false
        };
    },
    methods: {
        submitClick: function(formName) {
            this.$refs[formName].validate(valid => {
                if (valid) {
                    this.buttonDisabled = true;
                    userApi
                        .login(this.loginForm)
                        .then(res => {
                            if (res.data.status === 200) {
                                /**
                                 * 登录成功将token存储在localStorage中
                                 * 这里不这么做的原因是，已经在响应拦截做了处理
                                 */
                                //const token = res.data.data;
                                //localStorage.setItem("token", token);
                                // 现在登录成功返回的用户信息
                                localStorage.setItem(
                                    "user",
                                    JSON.stringify(res.data.data)
                                );
                                this.$store.dispatch("setAuthenticated", true);
                                this.$store.dispatch("setUser", res.data.data);
                                const path = this.$route.query.redirect;
                                this.$router.push(path == '/' || path == undefined ? '/index' : path);
                            }
                            this.buttonDisabled = false;
                        })
                        .catch(err => {
                            this.buttonDisabled = false;
                        });
                }
            });
        }
    }
};
</script>

<style>
.login {
    min-width: 422px;
    min-height: 680px;
    width: 100%;
    height: 100%;
    /*background: url(../assets/images/bg.jpg) no-repeat;*/
    background-size: 100% 100%;
}

.login-container {
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

.login_title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #505458;
}

.login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
}

.login_button {
    width: 48%;
    margin-top: 10px;
}
</style>