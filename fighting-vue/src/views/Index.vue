<template>
    <div class="index">
        <HeadNav></HeadNav>
        <LeftMenu></LeftMenu>
        <div class="rightContainer">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
// 引入页面
import HeadNav from "@/components/HeadNav";
import LeftMenu from "@/components/LeftMenu";

import userApi from "@/api/user";
import common from "@/utils/common";

import { initMenu } from '@/api/menu'

export default {
    name: "Index",
    components: {
        HeadNav,
        LeftMenu
    },
    created() {

    },
    data() {
        return {};
    },
    methods: {
        logout: function() {
            userApi
                .logout()
                .then(res => {
                    if (res.data.status === 200) {
                        common.clearTokenToLogin();
                    }
                })
                .catch(err => {});
        },
    }
};
</script>

<style scope>
.index {
    width: 100%;
    height: 100%;
    overflow: hidden;
}
.rightContainer {
    position: relative;
    top: 0;
    left: 180px;
    width: calc(100% - 180px);
    height: calc(100% - 61px);
    overflow: auto;
}
</style>