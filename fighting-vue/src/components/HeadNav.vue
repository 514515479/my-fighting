<template>
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
                        this.$msg.warning("功能开发中。。。");
                        break;
                    case "logout":
                        this.logout();
                        break;
                }
            },
            logout: function () {
                this.$confirm("您确定要退出登录吗?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    userApi.logout()
                        .then(res => {
                            common.clearTokenToLogin();
                        }).catch(err => {
                        common.clearTokenToLogin();
                    })
                }).catch(() => {
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