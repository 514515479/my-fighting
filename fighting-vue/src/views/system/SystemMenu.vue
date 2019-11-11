<template>
    <div class="fillContainer">
        <!--搜索栏-->
        <div>
            <el-form :inline="true" ref="searchForm" :model="searchForm" :rules="searchRule" @submit.native.prevent>
                <el-form-item prop="name" label="菜单名称">
                    <el-input
                            v-model="searchForm.name"
                            clearable
                            style="width: 200px;"
                            placeholder="请输入菜单名称"
                    ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="view" @click="handleSearch">查询</el-button>
                    <el-button type="warning" icon="view" @click="handleReset">重置</el-button>
                </el-form-item>
                <el-form-item class="btnRight">
                    <el-button type="success" icon="view" @click="refreshPermission">刷新权限</el-button>
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
                    :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
                <el-table-column
                        prop="name"
                        label="菜单名称"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="icon"
                        label="图标"
                        align="center"
                        width="60">
                    <template slot-scope="scope">
                        <i v-if="scope.row.icon"
                           :class="scope.row.icon"></i>
                        <span v-if="!scope.row.icon">无</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="type"
                        label="类型"
                        align="center"
                        width="80">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.type === '0'">目录</el-tag>
                        <el-tag v-else-if="scope.row.type === '1'" type="success">菜单</el-tag>
                        <el-tag v-else-if="scope.row.type === '2'" type="warning">权限</el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="parentName"
                        label="上级菜单"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="path"
                        label="菜单URL"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="component"
                        label="菜单组件"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="url"
                        label="接口URL"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="method"
                        label="接口方法"
                        align="center">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.method === 'GET'" type="success">GET</el-tag>
                        <el-tag v-else-if="scope.row.method === 'POST'">POST</el-tag>
                        <el-tag v-else-if="scope.row.method === 'PUT'" type="warning">PUT</el-tag>
                        <el-tag v-else-if="scope.row.method === 'DELETE'" type="danger">DELETE</el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="sort"
                        label="排序"
                        align="center"
                        width="50">
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
                    <el-form-item prop="type" label="菜单类型">
                        <el-radio-group v-model="dataForm.type" @change="radioChange()">
                            <el-radio v-for="(type, index) in menuTypeList"
                                      :key="index"
                                      :label="index + ''"
                            >{{ type }}
                            </el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item prop="icon" label="图标" v-if="dataForm.type !== '2'">
                        <el-input v-model="dataForm.icon" :suffix-icon="dataForm.icon"
                                  placeholder="输入正确后会出现图标预览效果"></el-input>
                    </el-form-item>
                    <el-form-item prop="name" :label="menuTypeList[dataForm.type] + '名称'">
                        <el-input v-model="dataForm.name" :placeholder="menuTypeList[dataForm.type] + '名称'"></el-input>
                    </el-form-item>
                    <el-form-item prop="parentName" label="上级菜单">
                        <popup-tree-input
                                :data="popupTreeData"
                                :props="popupTreeProps"
                                :prop="dataForm.parentName == null||dataForm.parentName === ''?'我是顶级':dataForm.parentName"
                                :node-key="''+dataForm.parentId"
                                :current-change-handle="handleTreeSelectChange"
                        ></popup-tree-input>
                    </el-form-item>
                    <el-form-item prop="path" label="菜单URL" v-if="dataForm.type != '2'">
                        <el-input v-model="dataForm.path" placeholder="菜单URL"></el-input>
                    </el-form-item>
                    <el-form-item prop="component" label="菜单组件" v-if="dataForm.type != '2'">
                        <el-input v-model="dataForm.component" placeholder="菜单组件"></el-input>
                    </el-form-item>
                    <el-form-item prop="url" label="接口URL" v-if="dataForm.type == '2'">
                        <el-input v-model="dataForm.url" placeholder="接口URL"></el-input>
                    </el-form-item>
                    <el-form-item prop="method" label="接口方法" v-if="dataForm.type == '2'">
                        <el-select v-model="dataForm.method" placeholder="请选择" clearable>
                            <el-option
                                    v-for="(item,index) in methodList"
                                    :key="item"
                                    :label="item"
                                    :value="item"
                            >
                            </el-option>
                        </el-select>
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
    import PopupTreeInput from '@/components/PopupTreeInput'

    import common from '@/utils/common'
    import menuApi from '@/api/menu'

    export default {
        name: 'SystemMenu',
        components: {
            PopupTreeInput
        },
        data() {
            return {
                searchForm: {
                    //pageNum: 1,  // 页码
                    //pageSize: 20,  // 数量
                    name: '',
                },
                searchRule: {
                    name: [
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                },
                tableData: [], // 表格数据

                // dialog部分
                dialog: {
                    show: false
                },
                dataForm: {
                    type: '1',
                    icon: '',
                    name: '',
                    parentId: 0,
                    parentName: '我是顶级',
                    path: '',
                    component: '',
                    url: '',
                    method: '',
                    sort: 1,
                },
                dataRule: {
                    icon: [
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    name: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    path: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    component: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    url: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    method: [
                        {required: true, message: "不能为空！", trigger: "change"},
                    ],
                },
                menuTypeList: ['目录', '菜单', '权限'],
                popupTreeData: [],
                popupTreeProps: {
                    label: 'name',
                    children: 'children'
                },
                methodList: ['GET', 'POST', 'PUT', 'DELETE']
            }
        },
        created() {
            this.loadData();
            this.getAllMenuTree();
        },
        methods: {
            // 获取表格数据
            loadData() {
                menuApi.menuTree(this.searchForm).then(res => {
                    this.tableData = res.data.data;
                }).catch(err => {
                })
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
            // 获取所有菜单数据
            getAllMenuTree() {
                menuApi.getAllMenuTree().then(res => {
                    const allMenuTree = res.data.data;
                    const parent = {
                        id: 0,
                        parentId: 0,
                        name: '我是顶级',
                        children: allMenuTree
                    }
                    this.popupTreeData = [parent];
                }).catch(err => {
                })
            },
            // 菜单类型切换的时候
            radioChange() {
                this.$refs['dataForm'].clearValidate();
            },
            // 刷新权限缓存
            refreshPermission() {
                this.$confirm('您确定要刷新权限缓存吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(res => {
                    menuApi.refreshPermission();
                }).catch(() => {
                })
            },
            // 添加
            handleAdd() {
                this.dataForm.id = null;
                this.dataForm = {
                    type: '1',
                    icon: '',
                    name: '',
                    parentId: 0,
                    parentName: '我是顶级',
                    path: '',
                    component: '',
                    url: '',
                    method: '',
                    sort: 1,
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
                    type: row.type,
                    icon: row.icon,
                    name: row.name,
                    parentId: row.parentId,
                    parentName: row.parentName,
                    path: row.path,
                    component: row.component,
                    url: row.url,
                    method: row.method,
                    sort: row.sort,
                }
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
                    menuApi.delete([row.id]).then(res => {
                        if (res.data.status === 200) {
                            this.loadData();
                            // 如果删除的是目录或者菜单则要重新加载菜单树
                            if (row.type !== 2) {
                                this.getAllMenuTree();
                            }
                        }
                    }).catch(err => {
                    })
                }).catch(() => {
                })
            },
            // 菜单树选中
            handleTreeSelectChange(data) {
                this.dataForm.parentId = data.id;
                this.dataForm.parentName = data.name;
            },
            // 新增修改操作
            submitForm() {
                const valid = common.validateForm(this.$refs['dataForm']);
                // 表单校验
                if (valid) {
                    if (this.dataForm.id) {// 修改
                        menuApi.update(this.dataForm).then(res => {
                            if (res.data.status === 200) {
                                this.dialog.show = false;
                                this.loadData();
                                // 如果新增的是目录或者菜单则要重新加载菜单树
                                if (this.dataForm.type !== 2) {
                                    this.getAllMenuTree();
                                }
                            }
                        }).catch(err => {
                        });
                    } else {// 新增
                        menuApi.save(this.dataForm).then(res => {
                            if (res.data.status === 200) {
                                this.dialog.show = false;
                                this.loadData();
                                // 如果修改的是目录或者菜单则要重新加载菜单树
                                if (this.dataForm.type !== 2) {
                                    this.getAllMenuTree();
                                }
                            }
                        }).catch(err => {
                        })
                    }
                }
            }
        }
    }
</script>