<template>
    <div class="fillContainer">
        <!--搜索栏-->
        <div>
            <el-form :inline="true" ref="searchForm" :model="searchForm" :rules="searchRule" @submit.native.prevent>
                <el-form-item prop="name" label="游客IP">
                    <el-input
                            v-model="searchForm.name"
                            clearable
                            style="width: 200px;"
                            placeholder="请输入游客IP"
                    ></el-input>
                </el-form-item>
                <el-form-item prop="nickname" label="用户昵称">
                    <el-input
                            v-model="searchForm.nickname"
                            clearable
                            style="width: 200px;"
                            placeholder="请输入用户昵称"
                    ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="view" @click="handleSearch">查询</el-button>
                    <el-button type="warning" icon="view" @click="handleReset">重置</el-button>
                </el-form-item>
                <el-form-item class="btnRight">
                    <el-button type="primary" icon="view" @click="handleAdd">添加</el-button>
                </el-form-item>
            </el-form>
        </div>

        <!--列表-->
        <div class="tableContainer">
            <el-table
                    :data="tableData"
                    style="width: 100%;margin-bottom: 10px;"
                    row-key="id"
                    border
                    :tree-props="{children: 'replyList', hasChildren: 'hasChildren'}"
                    :row-class-name="tableRowClassName">
                <el-table-column
                        label="楼层号"
                        width="120">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.remark !== null" type="danger">{{scope.row.remark}}楼</el-tag>
                        <el-tag v-if="scope.row.remark === null" type="success">回复</el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="留言游客IP"
                        width="140"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="nickname"
                        label="留言用户昵称"
                        width="150"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="color"
                        label="节点颜色"
                        width="120"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="icon"
                        label="节点图标"
                        width="150"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="content"
                        label="留言内容"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        label="留言时间"
                        width="180"
                        align="center">
                </el-table-column>
                <el-table-column
                        label="操作"
                        align="center"
                        fixed="right"
                        width="160">
                    <template slot-scope="scope">
                        <el-button size="small" type="warning" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    style="float: right;"
                    background
                    @size-change="handlePageSizeChange"
                    @current-change="handlePageNumChange"
                    :current-page.sync="searchForm.pageNum"
                    :page-size="searchForm.pageSize"
                    :page-sizes="[1, 10, 20, 50, 100]"
                    :total="totalSize"
                    layout="total, sizes, prev, pager, next, jumper"
            ></el-pagination>
        </div>


        <!--新增修改弹出框-->
        <div class="dialog">
            <el-dialog
                    :title="!dataForm.id ? '新增' : '修改'"
                    width="40%"
                    customClass="dialogCustomWidth"
                    :visible.sync="dialog.show"
                    :close-on-click-modal="false"
                    :modal-append-to-body="false"
            >
                <el-form
                        ref="dataForm"
                        :model="dataForm"
                        :rules="dataRule"
                        status-icon
                        label-width="100px"
                >
                    <el-form-item prop="color" label="节点颜色">
                        <el-input v-model="dataForm.color" placeholder="节点颜色"></el-input>
                    </el-form-item>
                    <el-form-item prop="icon" label="节点图标">
                        <el-input v-model="dataForm.icon" placeholder="节点图标"></el-input>
                    </el-form-item>
                    <el-form-item prop="content" label="留言内容">
                        <el-input
                                type="textarea"
                                :autosize="{ minRows: 4, maxRows: 4}"
                                maxlength="100"
                                show-word-limit
                                v-model="dataForm.content"
                                placeholder="留言内容"
                        ></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialog.show = false">取 消</el-button>
                    <el-button type="primary" @click="submitForm()">确 定</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import common from '@/utils/common'
    import messageApi from '@/api/message'

    export default {
        name: 'BlogMessage',
        data() {
            return {
                searchForm: {
                    pageNum: 1,  // 页码
                    pageSize: 10,  // 数量
                    name: '',
                    nickname: '',
                },
                searchRule: {
                    name: [
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    nickname: [
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                },
                tableData: [], // 表格数据
                totalSize: 0, // 表格数据总条数

                // dialog部分
                dialog: {
                    show: false
                },
                dataForm: {
                    color: '',
                    icon: '',
                    content: ''
                },
                dataRule: {
                    color: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    icon: [
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    content: [
                        {required: true, message: "回复不能为空！", trigger: "change"}
                    ],
                },
            }
        },
        created() {
            this.loadData();
        },
        methods: {
            // 获取表格数据
            loadData() {
                messageApi.list(this.searchForm).then(res => {
                    this.tableData = res.data.data.list;
                    this.totalSize = res.data.data.totalSize;
                }).catch(err => {
                })
            },
            // 换页
            handlePageNumChange(val) {
                this.searchForm.pageNum = val;
                this.loadData();
            },
            // 换数据条数
            handlePageSizeChange(val) {
                this.searchForm.pageSize = val;
                this.loadData();
            },
            tableRowClassName({row, rowIndex}) {
                if (row.remark === null) {
                    return 'success-row';
                } else {
                    return '';
                }
            },
            // 查询
            handleSearch() {
                const valid = common.validateForm(this.$refs['searchForm']);
                // 表单校验
                if (valid) {
                    this.loadData();
                }
            },
            // 重置
            handleReset() {
                this.$refs['searchForm'].resetFields();
                this.loadData();
            },
            // 添加
            handleAdd() {
                this.dataForm.id = null;
                this.dataForm = {
                    color: '',
                    icon: '',
                    content: ''
                };
                this.dialog.show = true;
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate();
                });
            },
            // 编辑
            handleEdit(index, row) {
                this.dataForm = {
                    id: row.id,
                    color: row.color,
                    icon: row.icon,
                    content: row.content
                };
                this.dialog.show = true;
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate();
                })
            },
            // 删除
            handleDelete(index, row) {
                this.$confirm('您确定要删除吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(res => {
                    messageApi.delete([row.id]).then(res => {
                        if (res.data.status === 200) {
                            this.loadData();
                        }
                    }).catch(err => {
                    })
                }).catch(() => {
                })
            },
            // 新增修改操作
            submitForm() {
                const valid = common.validateForm(this.$refs['dataForm']);
                // 表单校验
                if (valid) {
                    if (this.dataForm.id) {// 修改
                        messageApi.update(this.dataForm).then(res => {
                            if (res.data.status === 200) {
                                this.dialog.show = false;
                                this.loadData();
                            }
                        }).catch(err => {
                        });
                    } else {// 新增
                        messageApi.save(this.dataForm).then(res => {
                            if (res.data.status === 200) {
                                this.dialog.show = false;
                                this.loadData();
                            }
                        }).catch(err => {
                        })
                    }
                }
            }
        }

    }
</script>

<style>
    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }
</style>