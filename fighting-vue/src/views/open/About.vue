<template>
    <div>
        <el-container>
            <el-main>
                <el-card style="min-height: 82.7vh;">
                    <el-collapse accordion>
                        <el-collapse-item>
                            <template slot="title">
                                关于我 About me<i class="i-left-margin el-icon-s-custom"></i>
                            </template>
                            <div><h2>姓名：Kon</h2></div>
                            <div><h2>年龄：25</h2></div>
                            <div><h2>联系电话：18650477443</h2></div>
                            <div><h2>学历：专科（以前不会读书，现在感觉蛮可惜的~）</h2></div>
                            <div><h2>爱好：自由散漫（哈哈）</h2></div>
                            <div><h2>目前没有对象</h2></div>
                        </el-collapse-item>
                        <el-collapse-item>
                            <template slot="title">
                                技能 Skills<i class="i-left-margin el-icon-s-claim"></i>
                            </template>
                            <div><h2>我的目标：全方面发展（感觉不大可能，哈哈哈）</h2></div>
                            <div><h2>下面的技能都使用过，但了解不够深沉。</h2></div>
                            <div class="skill">
                                <el-tag effect="dark">Java</el-tag>
                                <el-tag effect="dark">Eclipse</el-tag>
                                <el-tag effect="dark">IDEA</el-tag>
                                <el-tag effect="dark">SVN</el-tag>
                                <el-tag effect="dark">Git</el-tag>
                                <el-tag effect="dark">Maven</el-tag>
                            </div>
                            <div class="skill">
                                <el-tag type="success" effect="dark">HTML</el-tag>
                                <el-tag type="success" effect="dark">CSS</el-tag>
                                <el-tag type="success" effect="dark">JavaScript</el-tag>
                                <el-tag type="success" effect="dark">JQuery</el-tag>
                                <el-tag type="success" effect="dark">Vue</el-tag>
                                <el-tag type="success" effect="dark">ElementUI</el-tag>
                            </div>
                            <div class="skill">
                                <el-tag type="danger" effect="dark">Oracle</el-tag>
                                <el-tag type="danger" effect="dark">Mysql</el-tag>
                                <el-tag type="danger" effect="dark">Redis</el-tag>
                                <el-tag type="danger" effect="dark">MemCache</el-tag>
                                <el-tag type="danger" effect="dark">MongoDB</el-tag>
                            </div>
                            <div class="skill">
                                <el-tag type="warning" effect="dark">Spring</el-tag>
                                <el-tag type="warning" effect="dark">SpringMVC</el-tag>
                                <el-tag type="warning" effect="dark">Mybatis</el-tag>
                                <el-tag type="warning" effect="dark">TkMybatis</el-tag>
                                <el-tag type="warning" effect="dark">MybatisPlus</el-tag>
                                <el-tag type="warning" effect="dark">SpringBoot</el-tag>
                                <el-tag type="warning" effect="dark">Dubbo</el-tag>
                                <el-tag type="warning" effect="dark">Zookeeper</el-tag>
                            </div>
                            <div class="skill">
                                <el-tag type="info" effect="dark">Linux</el-tag>
                                <el-tag type="info" effect="dark">Nginx</el-tag>
                                <el-tag type="info" effect="dark">RabbitMq</el-tag>
                                <el-tag type="info" effect="dark">MongoDB</el-tag>
                            </div>
                        </el-collapse-item>
                        <el-collapse-item>
                            <template slot="title">
                                经历 Experience<i class="i-left-margin el-icon-s-flag"></i>
                            </template>
                            <div><h2>电网谐波分析系统（负责业务模块开发）</h2></div>
                            <div><h2>输电线智能分析综合监控平台（负责业务模块开发）</h2></div>
                            <div><h2>新疆自治区精准扶贫平台（负责业务模块开发）</h2></div>
                            <div><h2>江西党建平台（负责业务模块开发）</h2></div>
                        </el-collapse-item>
                        <el-collapse-item>
                            <template slot="title">
                                博客 Blog<i class="i-left-margin el-icon-edit-outline"></i>
                            </template>
                            <div><h2>一直想有一个自己的博客</h2></div>
                            <div><h2>但是平时工作加班，也没静下心来，积累沉淀</h2></div>
                            <div><h2>工作之余也看了很多视频，了解了很多技术</h2></div>
                            <div><h2>但是基本都是看了丢，看了忘</h2></div>
                            <div><h2>感觉这样不是个事</h2></div>
                            <div><h2>必须找个地方把平时自己的点点滴滴积累起来</h2></div>
                            <div><h2>于是刚好趁这次机会，它就诞生了</h2></div>
                            <div><h2>目前前端使用Vue + ElmentUI，使用Nginx部署</h2></div>
                            <div><h2>目前后端使用的是SpringBoot + SpringSecurity + JWT + SpringCache + Redis + TkMybatis</h2></div>
                            <div><h2>以后工作中遇到的BUG，以及学习的笔记都会在此记录，希望它能见证我的程序员之路</h2></div>
                            <div><h2>我也会尽力去完善它</h2></div>
                            <div><h2>Fighting。。。</h2></div>
                        </el-collapse-item>
                    </el-collapse>
                </el-card>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "About",
        data() {
            return {}
        },
        methods: {
            async loadData() {
                return true;
            },
        }
    }
</script>

<style>
    .el-collapse-item__header {
        font-size: 16px;
    }

    .i-left-margin {
        margin-left: 10px;
    }

    .el-collapse-item__content {
        text-align: center;
        padding-left: 35px;
    }

    @media only screen and (max-width: 735px) {
        .el-collapse-item__content {
            padding-left: 0px;
        }
    }

    .skill .el-tag {
        margin: 10px;
    }
</style>