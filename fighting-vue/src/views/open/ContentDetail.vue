<template>
    <el-card class="content-detail">
        <el-link :underline="false" @click="goback()"><i class="el-icon-back">Back</i></el-link>
        <div class="content-detail-title">
            <h2 style="text-align: center">{{article.title}}</h2>
        </div>
        <div class="content-detail-info">
            <p class="content-detail-info-p">
                <span class="el-icon-time hidden-xs-only content-detail-info-createTime">&nbsp;{{formatterTime(article.createTime)}}</span>
                <span class="el-icon-view hidden-xs-only content-detail-info-view">&nbsp;{{article.view}}</span>
                <span class="el-icon-chat-line-square hidden-xs-only content-detail-info-discuss">&nbsp;{{article.discuss}}</span>
                <span class="el-icon-user-solid content-detail-info-user">&nbsp;{{article.nickname}}</span>
            </p>
        </div>
        <div class="content-detail-info">
            <p class="content-detail-info-p">
                <span v-for="tag in article.tagList">
                    <el-tag class="content-detail-info-tag" type="success">{{tag.name}}</el-tag>
                </span>
            </p>
        </div>
        <mavon-editor v-model="article.body" :toolbarsFlag="false" :subfield="false" defaultOpen="preview"
                      style="min-height: 31em;"></mavon-editor>
        <div class="hidden-xs-only content-detail-footer">
            <el-popover placement="bottom" width="250px" height="250px" trigger="hover">
                <img alt="打赏码" :src="article.remark" width="250px" height="250px"/>
                <el-button type="text" slot="reference" icon="el-icon-trophy" round>写的不错，打赏一个</el-button>
            </el-popover>
        </div>
        <el-divider></el-divider>
    </el-card>
</template>

<script>
    import dateUtils from "@/utils/date";
    import openApi from "@/api/open";

    export default {
        name: "ContentDetail",
        data() {
            return {
                id: this.$route.params.id,
                article: {},
            }
        },
        created() {
            this.loadData();
            this.viewArticle();
        },
        methods: {
            loadData() {
                openApi.getArticle(this.id).then(res => {
                    this.article = res.data.data;
                    if (this.article === null) {
                        this.article = {
                            title: '没有找到该文章',
                            view: 0,
                            discuss: 0,
                            nickname: '神秘作者',
                            body: '<h1 style="text-align: center">没有找到文章哪里来的内容</h1>',
                            remark: ''
                        }
                    }
                }).catch(err => {
                })
            },
            viewArticle() {
                const result = sessionStorage.getItem("viewArticle:" + this.id);
                if (result === null) {
                    sessionStorage.setItem("viewArticle:" + this.id, "true");
                    openApi.viewArticle(this.id).then(res => {}).catch(err => {});
                }
            },
            goback() {
                history.back()
            },
            formatterTime(timeStr) {
                return dateUtils.timeStrAgo(timeStr);
            },
        }
    }
</script>

<style>
    .content-detail {
        margin: 20px 20px 40px 20px;
        min-height: 48em;
    }

    @media only screen and (max-width: 735px) {
        .content-detail {
            margin: 0px;
            min-height: 48em;
        }
    }

    .content-detail-title {
        margin: 10px 0 20px 0;
    }

    .content-detail-info {
        text-align: center;
        margin: 10px 0 20px 0;
    }

    .content-detail-info-p {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .content-detail-info-createTime {
        min-width: 190px;
        width: 20%;
    }

    .content-detail-info-view {
        min-width: 50px;
        width: 10%;
    }

    .content-detail-info-discuss {
        min-width: 50px;
        width: 10%;
    }

    .content-detail-info-user {
        overflow: hidden;
        text-overflow: ellipsis;
        min-width: 150px;
        width: 10%;
    }

    .content-detail-info-tag {
        margin-left: 5px
    }

    .content-detail-footer {
        margin-top: 20px;
        text-align: center;
    }

</style>