<template>
    <el-row class="menu_page">
        <el-col>
            <el-menu
                    mode="vertical"
                    background-color="#324057"
                    text-color="#fff"
                    active-text-color="#409eff"
                    class="el-menu-vertical-demo"
            >
                <router-link to="/index">
                    <el-menu-item index="0">
                        <i class="el-icon-s-home"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>
                </router-link>
                <template v-for="item in menus">
                    <el-submenu
                            v-if="item.children && item.id !== 1"
                            :index="item.name"
                            :key="item.name"
                    >
                        <template slot="title">
                            <i :class="item.icon"></i>
                            <span slot="title">{{item.name}}</span>
                        </template>
                        <router-link
                                v-for="(citem,cindex) in item.children"
                                :to="item.path + '/' + citem.path"
                                :key="citem.path"
                        >
                            <el-menu-item :index="citem.path">
                                <i :class="citem.icon"></i>
                                <span slot="title">{{citem.name}}</span>
                            </el-menu-item>
                        </router-link>
                    </el-submenu>
                </template>
            </el-menu>
        </el-col>
    </el-row>
</template>
<script>
    export default {
        name: "leftmenu",
        computed: {
            menus() {
                return this.$store.getters.menus;
            }
        },
        data() {
            return {};
        }
    };
</script>

<style scope>
    .menu_page {
        position: fixed;
        top: 61px;
        left: 0;
        min-height: 100%;
        background-color: #324057;
        z-index: 99;
    }

    .el-menu {
        border: none;
    }

    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 180px;
        min-height: 400px;
    }

    .el-menu-vertical-demo {
        width: 35px;
    }

    .el-submenu .el-menu-item {
        min-width: 180px;
    }

    .hiddenDropdown,
    .hiddenDropname {
        display: none;
    }

    a {
        text-decoration: none;
    }
</style>
