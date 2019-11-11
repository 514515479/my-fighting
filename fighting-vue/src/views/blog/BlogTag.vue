<template>
    <div class="fillContainer">
        <!--搜索栏-->
        <div>
            <el-form :inline="true" ref="searchForm" :model="searchForm" :rules="searchRule" @submit.native.prevent>
                <el-form-item prop="name" label="标签名称">
                    <el-input
                            v-model="searchForm.name"
                            clearable
                            style="width: 200px;"
                            placeholder="标签名称"
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
            >
                <el-table-column prop="name" label="标签名称" align="center"></el-table-column>
                <el-table-column prop="sort" label="排序" align="center"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" align="center" width="180"></el-table-column>
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
                    top="5%"
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
                    <el-form-item prop="name" label="标签名称">
                        <el-input v-model="dataForm.name" placeholder="标签名称"></el-input>
                    </el-form-item>
                    <el-form-item prop="sort" label="排序">
                        <el-input-number
                                v-model="dataForm.sort"
                                controls-position="right"
                                :min="1"
                                :max="1000"
                                label="排序"
                        ></el-input-number>
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
    import tagApi from '@/api/tag'

    export default {
        name: 'BlogTag',
        components: {},
        data() {
            return {
                searchForm: {
                    pageNum: 1,  // 页码
                    pageSize: 10,  // 数量
                    username: '',
                },
                searchRule: {
                    name: [
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
                    name: '',
                    sort: 1
                },
                dataRule: {
                    name: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
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
                tagApi.list(this.searchForm).then(res => {
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
                    name: '',
                    sort: 1
                };
                this.dialog.show = true;
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate();
                })
            },
            // 编辑
            handleEdit(index, row) {
                this.dataForm = {
                    id: row.id,
                    name: row.name,
                    sort: row.sort
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
                    tagApi.delete([row.id]).then(res => {
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
                    if (this.dataForm.id) {
                        // 修改
                        tagApi.update(this.dataForm).then(res => {
                            if (res.data.status === 200) {
                                this.dialog.show = false;
                                this.loadData();
                            }
                        }).catch(err => {
                        });
                    } else {
                        // 新增
                        tagApi.save(this.dataForm).then(res => {
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