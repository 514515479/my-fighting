<template>
    <div class="fillContainer">
        <!--搜索栏-->
        <div>
            <el-form :inline="true" ref="searchForm" :model="searchForm" :rules="searchRule" @submit.native.prevent>
                <el-form-item prop="nameZh" label="角色中文名称">
                    <el-input
                            v-model="searchForm.nameZh"
                            clearable
                            style="width: 200px;"
                            placeholder="请输入角色中文名称"
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
            >
                <el-table-column prop="nameZh" label="角色中文名称" align="center"></el-table-column>
                <el-table-column prop="nameEn" label="角色英文名称" align="center"></el-table-column>
                <el-table-column prop="roleDesc" label="角色介绍" align="center"></el-table-column>
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
                        label-width="120px"
                >
                    <el-form-item prop="nameZh" label="角色中文名称">
                        <el-input v-model="dataForm.nameZh" placeholder="角色中文名称"></el-input>
                    </el-form-item>
                    <el-form-item prop="nameEn" label="角色英文名称">
                        <el-input v-model="dataForm.nameEn" placeholder="角色英文名称"></el-input>
                    </el-form-item>
                    <el-form-item prop="roleDesc" label="角色介绍">
                        <el-input v-model="dataForm.roleDesc" placeholder="角色介绍"></el-input>
                    </el-form-item>
                    <el-form-item label="访问权限" label-width="120px">
                        <el-tree
                                ref="menuTree"
                                v-loading="menuLoading"
                                :data="menuData"
                                size="mini"
                                show-checkbox
                                node-key="id"
                                :props="treeProps"
                                element-loading-text="拼命加载中"
                                :check-strictly="true"
                                @check-change="handleMenuCheckChange"
                        ></el-tree>
                        <div style="padding-left:24px;padding-top:12px;">
                            <el-checkbox v-model="checkAll" :disabled="false" @change="handleCheckAll"><b>全选</b>
                            </el-checkbox>
                        </div>
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
    import menuApi from '@/api/menu'
    import roleApi from '@/api/role'

    export default {
        name: 'SystemRole',
        components: {},
        data() {
            return {
                searchForm: {
                    pageNum: 1,  // 页码
                    pageSize: 10,  // 数量
                    username: '',
                },
                searchRule: {
                    nameZh: [
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
                    nameZh: '',
                    nameEn: '',
                    roleDesc: '',
                    menuIdList: []
                },
                checkAll: false,
                dataRule: {
                    nameZh: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    nameEn: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    roleDesc: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                },
                // 菜单权限数据
                menuData: [],
                menuLoading: false,
                // tree配置项
                treeProps: {
                    children: 'children',
                    label: 'name'
                },
            }
        },
        created() {
            this.loadData();
            this.loadMenuData();
        },
        methods: {
            // 获取表格数据
            loadData() {
                roleApi.list(this.searchForm).then(res => {
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
            // 加载菜单权限数据
            loadMenuData() {
                this.menuLoading = true;
                menuApi.menuTree().then(res => {
                    this.menuLoading = false;
                    this.menuData = res.data.data;
                }).catch(err => {
                })
            },
            // 树节点选择监听
            handleMenuCheckChange(data, check) {
                if (check) {
                    // 节点选中时同步选中父节点
                    const parentId = data.parentId;
                    this.$refs['menuTree'].setChecked(parentId, true, false)
                } else {
                    // 节点取消选中时同步取消选中子节点
                    if (data.children != null) {
                        data.children.forEach(element => {
                            this.$refs['menuTree'].setChecked(element.id, false, false)
                        })
                    }
                }
            },
            // 关闭所有节点
            closeTree() {
                const allNode = this.$refs['menuTree'].store._getAllNodes();
                for (let i = 0; i < allNode.length; i++) {
                    allNode[i].expanded = false;
                }
            },
            // 展开所有节点
            openTree() {
                const allNode = this.$refs['menuTree'].store._getAllNodes();
                for (let i = 0; i < allNode.length; i++) {
                    allNode[i].expanded = true;
                }
            },
            // 全选操作
            handleCheckAll() {
                if (this.checkAll) {
                    const allMenus = [];
                    this.checkAllMenu(this.menuData, allMenus);
                    this.$refs['menuTree'].setCheckedNodes(allMenus);
                } else {
                    this.$refs['menuTree'].setCheckedNodes([]);
                }
            },
            // 递归全选
            checkAllMenu(menuData, allMenus) {
                menuData.forEach(menu => {
                    allMenus.push(menu);
                    if (menu.children) {
                        this.checkAllMenu(menu.children, allMenus);
                    }
                })
            },
            // 回填节点
            async setTreeCheckedKeys(roleId) {
                // 查询该角色以有的权限
                const res = await roleApi.getRoleMenuIdList(roleId);
                const menuIdList = res.data.data;
                if (menuIdList.length > 0) {
                    this.$refs['menuTree'].setCheckedKeys(menuIdList);
                }
            },
            // 清空节点
            clearTreeCheckedKeys() {
                this.$refs['menuTree'].setCheckedKeys([]);
                this.checkAll = false;
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
                    nameZh: '',
                    nameEn: '',
                    roleDesc: '',
                    menuIdList: []
                };
                this.dialog.show = true;
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate();
                    // 清空节点
                    this.clearTreeCheckedKeys();
                    // 关闭所有节点
                    this.closeTree();
                })
            },
            // 编辑
            handleEdit(index, row) {
                this.dataForm = {
                    id: row.id,
                    nameZh: row.nameZh,
                    nameEn: row.nameEn,
                    roleDesc: row.roleDesc
                };
                this.dialog.show = true;
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate();
                    // 清空节点
                    this.$refs['menuTree'].setCheckedKeys([]);
                    // 打开所有节点
                    this.openTree();
                    // 回填选中节点
                    this.setTreeCheckedKeys(row.id);
                })
            },
            // 删除
            handleDelete(index, row) {
                this.$confirm('您确定要删除吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(res => {
                    roleApi.delete([row.id]).then(res => {
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
                    // 获取选中的节点Id数组
                    this.dataForm.menuIdList = this.$refs['menuTree'].getCheckedKeys();
                    if (this.dataForm.menuIdList.length === 0) {
                        this.$msg.warning("你为什么不选择权限？？？");
                        return;
                    }
                    if (this.dataForm.id) {
                        // 修改
                        roleApi.update(this.dataForm).then(res => {
                            if (res.data.status === 200) {
                                this.dialog.show = false;
                                this.loadData();
                            }
                        }).catch(err => {
                        });
                    } else {
                        // 新增
                        roleApi.save(this.dataForm).then(res => {
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
