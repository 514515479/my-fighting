<template>
    <el-container>
        留言板开发中。。。
    </el-container>
</template>

<script>
    export default {
        name: "MessageBoard",
        data() {
            return {

            }
        },
        methods: {
            // 加载对应数据,如果返回false,说明还有，如果返回true,没有更多
            async loadData() {
                console.log("加载留言板页数据");
                return true;
            },
        }
    }
</script>

<style>

</style>