<template>
    <div>
        <el-container v-infinite-scroll="load" infinite-scroll-disabled="disabled" infinite-scroll-delay="100">
            <el-main style="width: 75%; padding-bottom: 0px; padding-right: 15px;">
                <el-card class="search-card">
                    <el-form :inline="true" ref="searchForm" :model="searchForm" :rules="searchRule" @submit.native.prevent>
                        <el-row>
                            <el-col :xs="24" :lg="8">
                                <el-form-item prop="title" label="标题">
                                    <el-input
                                            v-model="searchForm.title"
                                            clearable
                                            placeholder="请输入标题"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :lg="8">
                                <el-form-item prop="tags" label="标签">
                                    <el-select v-model="searchForm.tags" placeholder="请选择标签" clearable>
                                        <el-option
                                                v-for="(item,index) in tagList"
                                                :key="item.id"
                                                :label="item.name"
                                                :value="item.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :lg="8">
                                <el-form-item style="text-align: center;">
                                    <el-button type="primary" icon="view" @click="handleSearch">查询</el-button>
                                    <el-button type="warning" icon="view" @click="handleReset">重置</el-button>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-card>
                <el-card shadow="hover" class="left-card" v-for="article in articleList" :key="article.id">
                    <div class="left-card-header">
                        <el-link :underline="false" @click="toArticle(article.id)">
                            <h2 class="left-card-title">{{article.title}}</h2>
                        </el-link>
                    </div>
                    <div class="left-card-body">
                        {{bodyTran(article.body)}}
                    </div>
                    <div class="left-card-footer">
                        <p class="left-card-footer-p">
                            <span class="el-icon-time hidden-xs-only left-card-footer-createTime">&nbsp;{{formatterTime(article.createTime)}}</span>
                            <span class="el-icon-view hidden-xs-only left-card-footer-view">&nbsp;{{article.view}}</span>
                            <span class="el-icon-chat-line-square hidden-xs-only left-card-footer-discuss">&nbsp;{{article.discuss}}</span>
                            <span class="el-icon-user-solid left-card-footer-user">&nbsp;{{article.nickname}}</span>
                            <span v-for="tag in article.tagList">
                            <el-tag class="left-card-footer-tag" type="success">{{tag.name}}</el-tag>
                        </span>
                        </p>
                    </div>
                </el-card>
            </el-main>
            <el-aside style="min-width: 200px; width: 20%; padding-top: 10px;" class="hidden-xs-only">
                <el-card shadow="hover" class="right-card">
                    <p>
                        <span class="el-icon-location-information right-card-title">本站介绍</span>
                    </p>
                    <hr/>
                    <div class="right-card-content">
                        <h1 v-html="introduction"></h1>
                    </div>
                    <br/>
                </el-card>
                <el-card shadow="hover" class="right-card">
                    <p>
                        <span class="el-icon-chat-line-square right-card-title">最新评论</span>
                    </p>
                    <hr/>
                    <div class="right-card-content" v-for="discuss in discussList">
                        <p @click="toArticle(discuss.articleId)">
                            {{discuss.name}} : {{discuss.content}}《{{discuss.articleTitle}}》
                        </p>
                    </div>
                    <div class="right-card-content" v-if="discussList.length === 0">
                        <h1>暂无最新评论</h1>
                    </div>
                </el-card>
                <el-card shadow="hover" class="right-card">
                    <p>
                        <span class="el-icon-document right-card-title">热门文章</span>
                    </p>
                    <hr/>
                    <div class="right-card-content" v-for="article in hotArticleList">
                        <p @click="toArticle(article.articleId)">
                            《{{ article.articleTitle }}》 浏览数 : {{ article.view }}
                        </p>
                    </div>
                    <div class="right-card-content" v-if="hotArticleList.length === 0">
                        <h1>暂无热门文章</h1>
                    </div>
                </el-card>
                <el-card shadow="hover" class="right-card">
                    <p>
                        <span class="el-icon-moon right-card-title">文章归档</span>
                    </p>
                    <hr/>
                    <div class="right-card-content" v-for="pigeonhole in articlePigeonholeList">
                        <p @click="noClick()">
                            {{ pigeonhole.year }}年{{ pigeonhole.month }}月-{{ pigeonhole.count }}篇
                        </p>
                    </div>
                    <div class="right-card-content" v-if="articlePigeonholeList.length === 0">
                        <h1>暂无文章归档</h1>
                    </div>
                </el-card>
            </el-aside>
        </el-container>
        <div style="text-align: center">
            <h4 v-if="loading" style="height: 30px; line-height: 30px;">拼命加载中。。。</h4>
            <h4 v-if="noMore" style="height: 30px; line-height: 30px;">我到底啦。。。</h4>
        </div>
    </div>
</template>

<script>
    import common from '@/utils/common'
    import dateUtils from "@/utils/date"
    import openApi from "@/api/open"
    import tagApi from '@/api/tag'

    export default {
        name: "ContentList",
        data() {
            return {
                searchForm: {
                    pageNum: 1,  // 页码
                    pageSize: 5,  // 数量
                    title: '',
                    tags: '',
                },
                searchRule: {
                    title: [
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                },
                // 标签集合
                tagList: [],

                // 左边
                articleList: [],

                // 右边
                introduction: 'Fighting<br />冲鸭 ! ',
                discussList: [
                    /*{
                        name: '昵称',
                        content: '评论内容',
                        articleId: 666,
                        articleTitle: '文章标题',
                    }*/
                ],
                hotArticleList: [
                    /*{
                        articleId: 666,
                        articleTitle: '文章标题',
                        view: 666,
                    }*/
                ],
                articlePigeonholeList: [
                    /*{
                        year: 2019,
                        month: 11,
                        count: 1,
                    }*/
                ],
                // 无限滚动
                loading: false,
                noMore: false,
            }
        },
        // created:在模板渲染成html前调用，即通常初始化某些属性值，然后再渲染成视图。
        // mounted:在模板渲染成html后调用，通常是初始化页面完成后，再对html的dom节点进行一些需要的操作。
        created() {
            // 获取所有文章标签
            this.getAllTag();
        },
        methods: {
            getAllTag() {
                tagApi.getAllTag().then(res => {
                    this.tagList = res.data.data;
                }).catch(err => {
                })
            },
            /**
             * 如果是条件查询则为true，否则为false
             * @param type
             * @returns {Promise<boolean>}
             */
            async loadData(type) {
                // 如果是查询，则重置当前页
                if (type) {
                    this.searchForm.pageNum = 1;
                }
                const res = await openApi.getArticleList(this.searchForm);
                if (res.data.status !== 200) {
                    return true;
                }
                this.searchForm.pageNum++;
                const list = res.data.data.list;
                // 如果是条件查询，直接覆盖list，否则未添加
                if (type) {
                    this.articleList = list;
                } else {
                    this.articleList.push.apply(this.articleList, list);
                }
                return list.length === this.searchForm.pageSize ? false : true;
            },
            // 查询
            handleSearch() {
                const valid = common.validateForm(this.$refs['searchForm']);
                // 表单校验
                if (valid) {
                    this.load(true);
                }
            },
            // 重置
            handleReset() {
                this.$refs['searchForm'].resetFields();
                this.load(true);
            },
            toArticle(id) {
                this.$router.push({ //路由跳转
                    path: '/open/contentDetail/' + id
                });
            },
            noClick() {
                this.$msg.error("别点我。。。")
            },
            // 将数据库中带标签的博文转换为纯文本
            bodyTran(body) {
                if (body === undefined) {
                    return '';
                }
                let dd = body.replace(new RegExp('#', 'g'), "");
                dd = dd.replace(new RegExp('!\\[.*\\]\\(.*\\)', 'g'), "[图片]");
                dd = dd.replace(new RegExp('\\[.*\\]\\(.*\\)', 'g'), "[链接]");
                dd = dd.replace(new RegExp('-', 'g'), "");
                dd = dd.replace(new RegExp('>', 'g'), "");
                dd = dd.replace(new RegExp('`', 'g'), "");
                if (dd.length > 50) {
                    return dd.substring(0, 100) + '...';
                }
                return dd;
            },
            formatterTime(timeStr) {
                return dateUtils.timeStrAgo(timeStr);
            },
            load(type) {
                this.noMore = false;
                this.loading = true;
                setTimeout(() => {
                    this.loadData(type)
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
            }
        },
        computed: {
            disabled() {
                return this.loading || this.noMore;
            }
        },
    }
</script>

<style>
    .el-card .el-form .el-form-item {
        margin-bottom: 20px;
    }

    .search-card .el-card__body {
        padding-bottom: 0px;
    }

    .search-card .el-form-item {
        width: 100%;
    }

    .search-card .el-form-item .el-form-item__content {
        width: 80%;
    }

    .search-card .el-form-item .el-form-item__content .el-select {
        width: 100%;
    }

    .left-card {
        margin-top: 10px;
    }

    .el-card.left-card.is-hover-shadow:hover {
        box-shadow: 0 2px 12px 0 rgba(97, 255, 33, 0.5);
    }

    .left-card-header {
        margin: 15px 0;
    }

    .left-card-title {
        color: black;
        transition: all 0.5s linear;
    }

    .left-card-title:hover {
        color: #409EFF;
        transition: all 0.5s linear;
    }

    .left-card-body {
        margin: 20px 0;
        font-size: 14px;
        line-height: normal;
    }

    .left-card-footer-p {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .left-card-footer-createTime {
        min-width: 190px;
        width: 20%;
    }

    .left-card-footer-view {
        min-width: 50px;
        width: 10%;
    }

    .left-card-footer-discuss {
        min-width: 50px;
        width: 10%;
    }

    .left-card-footer-user {
        overflow: hidden;
        text-overflow: ellipsis;
        min-width: 150px;
        width: 10%;
    }

    .left-card-footer-tag {
        margin-left: 5px
    }

    .right-card {
        margin: 10px 10px 0px 5px;
        text-align: center;
    }

    .el-card.right-card.is-hover-shadow:hover {
        box-shadow: 0 2px 12px 0 rgba(0, 134, 255, 0.5);
    }

    .right-card-title {
        color: #67C23A;
        margin: 10px;
    }

    .right-card-content {
        line-height: normal;
    }

    .right-card-content p {
        cursor: pointer;
    }
</style>