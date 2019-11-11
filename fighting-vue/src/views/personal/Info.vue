<template>
    <div class="info-show">
        <el-row type="flex" class="row-bg" justify="center">
            <el-col :span="8">
                <div class="personal">
                    <img :src="user.avatar" class="personal-avatar" alt />
                </div>
            </el-col>
            <el-col :span="16">
                <div class="personal-info">
                    <div class="personal-item">
                        <i class="el-icon-s-custom icon-margin"></i>
                        <span>{{user.nickname}}</span>
                    </div>
                    <div class="personal-item" v-for="(role,index) in user.roles" :key="index">
                        <i class="el-icon-s-tools icon-margin"></i>
                        <span>{{role.nameZh}}</span>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
<script>
export default {
    name: "PersonalInfo",
    computed: {
        user() {
            return this.$store.getters.user;
        }
    }
};
</script>
<style>
.info-show {
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    /* padding: 16px; */
}
.row-bg {
    width: 100%;
    height: 100%;
}
.personal {
    text-align: center;
    position: relative;
    top: 30%;
}
.personal img {
    width: 150px;
    border-radius: 50%;
}
.personal span {
    display: block;
    text-align: center;
    margin-top: 20px;
    font-size: 20px;
    font-weight: bold;
}
.personal-info {
    height: 100%;
    background-color: #eee;
}
.personal-item {
    position: relative;
    top: 30%;
    padding: 26px;
    font-size: 28px;
    color: #333;
}
.icon-margin {
    margin-right: 10px;
}
</style>
