<template>
    <div>
        <el-container v-infinite-scroll="load" infinite-scroll-disabled="disabled" infinite-scroll-distance="60">
            <el-main>
                <el-card class="data-card">
                    <el-form ref="dataForm" :model="dataForm" :rules="dataRule" @submit.native.prevent>
                        <el-form-item prop="content" label="留言">
                            <el-input
                                    type="textarea"
                                    :autosize="{ minRows: 4, maxRows: 4}"
                                    maxlength="100"
                                    show-word-limit
                                    v-model="dataForm.content"
                                    placeholder="请输入留言"
                            ></el-input>
                        </el-form-item>
                        <el-form-item style="text-align: right;">
                            <el-button type="success" icon="view" @click="load(true)">刷新</el-button>
                            <el-button type="warning" icon="view" @click="handleEmpty">清空</el-button>
                            <el-button type="primary" icon="view" @click="handlePublish">发布</el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
                <div class="message-board">
                    <el-timeline>
                        <template v-for="(msg,index) in messageList">
                            <el-timeline-item :timestamp="msg.createTime" placement="top" size="large"
                                              :color="msg.color">
                                <el-card>
                                    <p class="msg" v-if="msg.name !== null">
                                        <span class="floor-num">{{total - index}} 楼</span>网友：{{msg.name}}
                                        &nbsp;<el-button v-if="$store.state.isAuthenticated === true"
                                                         type="success"
                                                         size="mini" icon="view"
                                                         @click="reply(msg.id)">回复
                                        </el-button>
                                    </p>
                                    <p class="msg" v-if="msg.name === null">
                                        <span class="floor-num">{{total - index}} 楼</span>留言者：{{msg.nickname}}
                                        &nbsp;<el-button v-if="$store.state.isAuthenticated === true"
                                                         type="success"
                                                         size="mini" icon="view"
                                                         @click="reply(msg.id)">回复
                                        </el-button>
                                    </p>
                                    <h3>{{msg.content}}</h3>
                                    <el-divider></el-divider>
                                    <template v-for="reply in msg.replyList">
                                        <h4>{{reply.nickname}} 回复说：{{reply.content}}</h4>
                                    </template>
                                </el-card>
                            </el-timeline-item>
                        </template>
                    </el-timeline>
                </div>
                <div style="text-align: center">
                    <h4 v-if="loading" style="height: 30px; line-height: 30px;">拼命加载中。。。</h4>
                    <h4 v-if="noMore" style="height: 30px; line-height: 30px;">我到底啦。。。</h4>
                </div>
            </el-main>
        </el-container>
        <!--新增修改弹出框-->
        <div class="dialog">
            <el-dialog
                    title="我该说点啥。。。"
                    top="30vh"
                    customClass="dialogCustomWidth"
                    :visible.sync="dialog.show"
                    :modal-append-to-body="false"
            >
                <el-form ref="replyForm" :model="replyForm" :rules="replyRule" @submit.native.prevent>
                    <el-form-item prop="content" label="回复">
                        <el-input
                                type="textarea"
                                :autosize="{ minRows: 4, maxRows: 4}"
                                maxlength="100"
                                show-word-limit
                                v-model="replyForm.content"
                                placeholder="请输入回复"
                        ></el-input>
                    </el-form-item>
                    <el-form-item style="text-align: right;">
                        <el-button type="warning" icon="view" @click="handleReplyEmpty">清空</el-button>
                        <el-button type="primary" icon="view" @click="handleReply">回复</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import common from '@/utils/common'
    import openApi from "@/api/open"

    export default {
        name: "MessageBoard",
        data() {
            return {
                searchForm: {
                    pageNum: 1,  // 页码
                    pageSize: 5  // 数量
                },
                total: 0,
                dataForm: {
                    content: '',
                    parentId: 0,
                },
                dataRule: {
                    content: [
                        {required: true, message: "留言不能为空！", trigger: "change"}
                    ],
                },
                messageList: [],
                // dialog部分
                dialog: {
                    show: false
                },
                replyForm: {
                    content: '',
                    parentId: null,
                },
                replyRule: {
                    content: [
                        {required: true, message: "回复不能为空！", trigger: "change"}
                    ],
                },
                // 无限滚动
                loading: false,
                noMore: false,
            }
        },
        created() {

        },
        methods: {
            async loadData(type) {
                // 如果是查询，则重置当前页
                if (type) {
                    this.searchForm.pageNum = 1;
                }
                const res = await openApi.msgPage(this.searchForm);
                if (res.data.status !== 200) {
                    return true;
                }
                this.searchForm.pageNum++;
                const list = res.data.data.list;
                this.total = res.data.data.totalSize;
                // 如果是条件查询，直接覆盖list，否则未添加
                if (type) {
                    this.messageList = list;
                } else {
                    this.messageList.push.apply(this.messageList, list);
                }
                return list.length === this.searchForm.pageSize ? false : true;
            },
            // 清空
            handleEmpty() {
                this.$refs['dataForm'].resetFields();
            },
            // 发布
            handlePublish() {
                const valid = common.validateForm(this.$refs['dataForm']);
                // 表单校验
                if (valid) {
                    openApi.leaveMsg(this.dataForm).then(res => {
                        if (res.data.status === 200) {
                            this.$refs['dataForm'].resetFields();
                            this.load(true);
                        }
                    }).catch(err => {
                    });
                }
            },
            // 回复按钮
            reply(id) {
                this.replyForm.parentId = id;
                this.replyForm.content = '';
                this.dialog.show = true;
                this.$nextTick(() => {
                    this.$refs['replyForm'].clearValidate();
                })
            },
            // 清空
            handleReplyEmpty() {
                this.replyForm.content = '';
            },
            // 回复
            handleReply(id) {
                const valid = common.validateForm(this.$refs['replyForm']);
                // 表单校验
                if (valid) {
                    openApi.replyMsg(this.replyForm).then(res => {
                        if (res.data.status === 200) {
                            this.dialog.show = false;
                            this.load(true);
                        }
                    }).catch(err => {
                    });
                }
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
                }, 100);
            }
        },
        computed: {
            disabled() {
                // 因为加了<keep-alive>，所以在disabled时候判断一下
                if (this.$route.path !== '/open/messageBoard') {
                    return true;
                }
                return this.loading || this.noMore;
            }
        },
    }
</script>

<style>
    .data-card .el-card__body {
        padding-bottom: 0px;
    }

    .el-timeline-item__tail {
        border-left: 3px solid #66b1ff;
    }

    .message-board {
        margin-top: 20px;
    }

    .message-board h3 {
        margin: 20px;
    }

    .message-board h4 {
        margin: 10px;
    }

    .msg {
        text-align: right;
        font-size: 14px;
        color: #5e6d82;
        line-height: 1.5em;
    }

    .msg .floor-num {
        float: left;
        font-size: 14px;
        font-weight: bold;
        color: #fa5a5a;
    }
</style>