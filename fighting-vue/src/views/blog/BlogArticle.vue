<template>
    <div class="fillContainer">
        <!--搜索栏-->
        <div>
            <el-form :inline="true" ref="searchForm" :model="searchForm" :rules="searchRule" @submit.native.prevent>
                <el-form-item prop="title" label="标题">
                    <el-input
                            v-model="searchForm.title"
                            clearable
                            style="width: 200px;"
                            placeholder="请输入标题"
                    ></el-input>
                </el-form-item>
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
                <el-form-item prop="draftFlag" label="文章状态">
                    <el-select v-model="searchForm.draftFlag" placeholder="请选择文章状态" clearable>
                        <el-option value="0" label="发布">发布</el-option>
                        <el-option value="1" label="草稿">草稿</el-option>
                    </el-select>
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
                <el-table-column prop="title" label="标题" align="center"></el-table-column>
                <el-table-column label="文章标签" align="center">
                    <template slot-scope="scope">
                        <el-tag v-for="item in scope.row.tagList" :key="item.id" type="success"
                                style="margin-right: 5px;">
                            {{ item.name }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="discuss" label="评论数" align="center" width="100"></el-table-column>
                <el-table-column prop="view" label="浏览数" align="center" width="100"></el-table-column>
                <el-table-column prop="draftFlag" label="文章状态" align="center" width="100">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.draftFlag" type="warning">草稿</el-tag>
                        <el-tag v-else-if="!scope.row.draftFlag">发布</el-tag>
                    </template>
                </el-table-column>
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
                    top="2%"
                    width="80%"
                    customClass="dialogCustomWidth"
                    :visible.sync="dialog.show"
                    :close-on-click-modal="false"
                    :modal-append-to-body="false"
                    :destroy-on-close="true"
            >
                <el-input v-model="dataForm.title" placeholder="请输入标题" maxlength="30" show-word-limit></el-input>
                <mavon-editor ref=md
                              class="md-editor"
                              v-model="dataForm.body"
                              @save="save()"
                              @imgAdd="$uploadImg"
                              @imgDel="$imgDel"
                              placeholder="## Start">
                </mavon-editor>

                <el-card class="tag-card">
                    <p v-if="tagList.length > 0" class="el-icon-mouse">选择文章标签</p>
                    <p v-if="tagList.length === 0" class="el-icon-close">请先添加文章标签</p>
                    <el-checkbox-group v-model="dataForm.tagIdList">
                        <el-checkbox v-for="tag in tagList"
                                     :key="tag.id"
                                     :label="tag.id"
                                     style="margin-top: 20px;"
                                     border>
                            {{tag.name}}
                        </el-checkbox>
                    </el-checkbox-group>
                </el-card>
                <span slot="footer" class="dialog-footer">
                    <el-switch
                            style="float: left; margin-top: 10px;"
                            v-model="dataForm.draftFlag"
                            active-text="草稿"
                            inactive-text="发布">
                    </el-switch>
                    <el-button @click="dialog.show = false">取 消</el-button>
                    <el-button type="primary" @click="submitForm()">确定</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import common from '@/utils/common'
    import articleApi from '@/api/article'
    import tagApi from '@/api/tag'
    import fileApi from '@/api/file'

    export default {
        name: 'BlogArticle',
        components: {},
        data() {
            return {
                searchForm: {
                    pageNum: 1,  // 页码
                    pageSize: 10,  // 数量
                    title: '',
                    tags: '',
                    draftFlag: null,
                },
                searchRule: {
                    title: [
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
                    title: '',
                    body: '',
                    tagIdList: [],
                    draftFlag: false,
                },
                dataRule: {},
                tagList: [],
            }
        },
        created() {
            this.loadData();
            this.getAllTag();
        },
        methods: {
            // 获取表格数据
            loadData() {
                articleApi.list(this.searchForm).then(res => {
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
            // 获取所有文章标签
            getAllTag() {
                tagApi.getAllTag().then(res => {
                    this.tagList = res.data.data;
                }).catch(err => {
                })
            },
            //***************mavon-editor******************
            // 保存校验
            saveCheck() {
                if (this.dataForm.title.length === 0) {
                    this.$msg.warning("请输入标题");
                    return false;
                }
                if (this.dataForm.body.length === 0) {
                    this.$msg.warning("请输入内容");
                    return false;
                }
                return true;
            },
            // 保存
            save() {
                if (!this.saveCheck()) return;
                fileApi.generateTxt(this.dataForm.title, this.dataForm.body + '\n保存时间为: ' + new Date().toLocaleString());
            },
            // 图片上传
            $uploadImg(pos, $file) {
                // 第一步.将图片上传到服务器.
                const formData = new FormData();
                formData.append('file', $file);
                //this.dataForm.img_file[pos] = $file;
                fileApi.uploadImg(formData).then(res => {
                    // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
                    this.$refs.md.$img2Url(pos, res.data.data);
                })
            },
            // 图片文件删除回调事件
            $imgDel(pos) {
                //delete this.dataForm.img_file[pos];
            },
            //***************mavon-editor******************
            // 添加
            handleAdd() {
                this.dataForm.id = null;
                this.dataForm = {
                    title: '',
                    body: '',
                    tagIdList: [],
                    draftFlag: false,
                };
                this.dialog.show = true;
            },
            // 编辑
            handleEdit(index, row) {
                this.dataForm = {
                    id: row.id,
                    title: row.title,
                    body: row.body,
                    tagIdList: [],
                    draftFlag: row.draftFlag,
                };
                row.tagList.forEach(item => {
                    this.dataForm.tagIdList.push(item.id);
                });
                this.dialog.show = true;
            },
            // 删除
            handleDelete(index, row) {
                this.$confirm('您确定要删除吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(res => {
                    articleApi.delete([row.id]).then(res => {
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
                if (!this.submitCheck()) return;
                if (this.dataForm.id) {
                    // 修改
                    articleApi.update(this.dataForm).then(res => {
                        if (res.data.status === 200) {
                            this.dialog.show = false;
                            this.loadData();
                        }
                    }).catch(err => {
                    });
                } else {
                    // 新增
                    articleApi.save(this.dataForm).then(res => {
                        if (res.data.status === 200) {
                            this.dialog.show = false;
                            this.loadData();
                        }
                    }).catch(err => {
                    })
                }
            },
            // 保存修改文章校验
            submitCheck() {
                if (!this.saveCheck()) {
                    return false;
                }
                if (this.dataForm.tagIdList.length === 0) {
                    this.$msg.warning("请选择文章标签");
                    return false;
                }
                return true;
            },
        }
    }
</script>

<style>
    .md-editor {
        margin-top: 20px;
        height: 500px;
    }

    .tag-card {
        margin-top: 20px;
        text-align: center;
    }
</style>