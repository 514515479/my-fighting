<template>
    <div class="welcomeContainer">
        <el-backtop target=".welcomeContainer" :right="15" :bottom="15" style="z-index: 2000;">
            <div class="back-top">UP</div>
        </el-backtop>
        <div class="f-header">
            <el-card class="f-card">
                <el-menu :default-active="menuActiveIndex"
                         class="el-menu-demo"
                         mode="horizontal"
                         :router="true"
                         @select="handleSelect">
                    <el-menu-item index="1" route="/">文章</el-menu-item>
                    <el-menu-item index="2" route="/open/messageBoard">留言板</el-menu-item>
                    <el-menu-item index="3" route="/open/about">关于</el-menu-item>
                    <el-menu-item index="4" route="/login" class="item-right"
                                  v-if="$store.state.isAuthenticated === false">
                        Login
                    </el-menu-item>
                    <el-menu-item index="5" route="/index" class="item-right"
                                  v-if="$store.state.isAuthenticated === true">
                        首页
                    </el-menu-item>
                </el-menu>
            </el-card>
        </div>
        <div class="f-content">
            <keep-alive>
                <router-view v-if="$route.meta.keepAlive"></router-view>
            </keep-alive>
            <router-view v-if="!$route.meta.keepAlive"></router-view>
        </div>
        <div class="f-footer hidden-xs-only">
            <Bottom></Bottom>
        </div>
    </div>
</template>

<script>
    // 引入页面
    import Bottom from "@/views/open/Bottom";

    export default {
        name: "Welcome",
        components: {
            Bottom
        },
        data() {
            return {
                menuActiveIndex: '1',
            }
        },
        watch: {
            // 监控当前页面path，防止刷新页面显示错误
            '$route.path': {
                deep: true,
                immediate: true,
                handler(to, from) {
                    if (to === '/') {
                        this.menuActiveIndex = '1';
                    } else if (to === '/open/messageBoard') {
                        this.menuActiveIndex = '2';
                    } else if (to === '/open/about') {
                        this.menuActiveIndex = '3';
                    } else {
                        this.menuActiveIndex = '';
                    }
                    this.$nextTick(() => {
                        document.getElementsByClassName("welcomeContainer")[0].scrollTop = 0;
                    });
                }
            }
        },
        methods: {
            handleSelect(key, keyPath) {
                if (key != null && key !== '') {
                    this.menuActiveIndex = key;
                }
            },
        }
    }
</script>

<style>
    .welcomeContainer {
        width: 100%;
        height: 100%;
        overflow: auto;
        background: beige;
    }

    .item-right {
        float: right !important;
        color: cornflowerblue;
        font-weight: bold
    }

    .back-top {
        height: 100%;
        width: 100%;
        background-color: #f2f5f6;
        box-shadow: 0 0 6px rgba(0, 0, 0, .12);
        text-align: center;
        line-height: 40px;
        color: #1989fa;
    }

    .f-header {
        width: 100%;
        height: 60px;
        position: fixed;
        top: 0;
        z-index: 1501;
    }

    .f-header .el-card__body {
        height: 100%;
        padding: 0 10px;
    }

    .f-card {
        height: 100%;
        margin: 0;
    }

    .f-content {
        padding: 60px 0;
    }

    .f-footer {
        width: 100%;
        height: 60px;
        position: fixed;
        bottom: 0;
        z-index: 1501;
    }

    @media only screen and (max-width: 735px) {
        .f-content {
            padding: 60px 0 0 0;
        }
    }
</style>