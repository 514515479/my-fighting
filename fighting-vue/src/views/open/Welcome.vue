<template>
    <div class="welcomeContainer" v-infinite-scroll="load" infinite-scroll-disabled="disabled">
        <el-backtop target=".welcomeContainer" :right="40" :bottom="90" style="z-index: 2000;">
            <div class="back-top">UP</div>
        </el-backtop>
        <div class="f-header">
            <el-card class="f-card">
                <el-menu :default-active="menuActiveIndex" class="el-menu-demo" :router="true" mode="horizontal"
                         @select="handleSelect">
                    <el-menu-item index="1" route="/">文章</el-menu-item>
                    <el-menu-item index="2" route="/open/messageBoard">留言板</el-menu-item>
                    <el-menu-item index="3" route="/open/announcement">公告</el-menu-item>
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
            <router-view ref="targetView"/>
            <div style="margin-top: -25px;text-align: center;">
                <h4 v-if="loading" style="height: 30px;line-height: 30px">加载中。。。</h4>
                <h4 v-if="noMore" style="height: 30px;line-height: 30px">没有更多了。。。</h4>
            </div>
        </div>
        <div class="f-footer">
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
                loading: false,
                noMore: false,
                // 该变量用来处理有些页面不需要loadData
                dynamic: true,
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
                    } else if (to === '/open/announcement') {
                        this.menuActiveIndex = '3';
                    } else {
                        this.menuActiveIndex = '';
                    }
                    if (to.indexOf('/open/contentDetail') === -1) {
                        this.dynamic = false;
                        this.loading = false;
                        this.noMore = false;
                        this.load();
                    } else {
                        this.dynamic = true;
                        this.loading = false;
                        this.noMore = false;
                    }
                }
            }
        },
        computed: {
            disabled() {
                return this.loading || this.noMore || this.dynamic;
            }
        },
        methods: {
            handleSelect(key, keyPath) {
                if (key != null && key !== '') {
                    this.menuActiveIndex = key;
                }
            },
            load() {
                this.loading = true;
                setTimeout(() => {
                    this.$refs['targetView'].loadData()
                        .then(result => {
                            // 是否没有数据了
                            this.noMore = result;
                            this.loading = false;
                        })
                        .catch(err => {
                            this.noMore = true;
                            this.loading = false;
                        });
                }, 1000);
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
        font-weight:bold
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
        height: 80px;
        position: fixed;
        top: 0;
        z-index: 2000;
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
        padding: 90px 0;
    }

    .f-footer {
        width: 100%;
        height: 80px;
        position: fixed;
        bottom: 0;
        z-index: 2000;
    }
</style>