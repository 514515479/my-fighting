<template>
    <div class="not-found">
        <div class="container">
            <h1 class="title">您访问的页面被风吹走了！</h1>
            <el-button class="lead" type="warning" round @click="goIndex()">回首页</el-button>
        </div>
    </div>
</template>

<script>
import common from "@/utils/common";

export default {
    methods: {
        goIndex() {
            common.toIndex();
        }
    }
}
</script>

<style>
.not-found {
    width: 100%;
    height: 100%;
    /*background: url(../assets/images/404.gif) no-repeat;*/
    background-size: 100% 100%;
}

.container {
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    padding-top: 100px;
    text-align: center;
}
.title {
    font-size: 30px;
}
.lead {
    margin-top: 50px;
    font-size: 30px;
}
</style>