<template>
    <div class="fillContainer">
        <!--搜索栏-->
        <div>
            <el-form :inline="true" ref="searchForm" :model="searchForm" :rules="searchRule" @submit.native.prevent>
                <el-form-item prop="username" label="用户名">
                    <el-input
                            v-model="searchForm.username"
                            clearable
                            style="width: 200px;"
                            placeholder="请输入用户名"
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
                <el-table-column prop="username" label="用户名" align="center"></el-table-column>
                <el-table-column prop="avatar" label="头像" align="center" width="110">
                    <template slot-scope="scope">
                        <div style="display:inline-block">
                            <img :src="scope.row.avatar" class="form-avatar">
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="nickname" label="昵称" align="center"></el-table-column>
                <el-table-column prop="phone" label="手机号" align="center"></el-table-column>
                <el-table-column prop="email" label="邮箱" align="center"></el-table-column>
                <el-table-column label="拥有角色" align="center">
                    <template slot-scope="scope">
                        <el-tag v-for="item in scope.row.roles" :key="item.id" type="success"
                                style="margin-right: 5px;">
                            {{ item.nameZh }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="enabledFlag" label="账号状态" align="center" width="100">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.enabledFlag" type="success">启用</el-tag>
                        <el-tag v-else-if="!scope.row.enabledFlag" type="danger">禁用</el-tag>
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
                    <el-form-item prop="avatar" label="头像">
                        <el-upload
                                class="avatar-uploader"
                                :action="uploadUrl"
                                :headers="token"
                                :show-file-list="false"
                                :before-upload="beforeAvatarUpload"
                                :on-success="handleAvatarSuccess"
                                :on-error="handleAvatarError">
                            <img v-if="dataForm.avatar" :src="dataForm.avatar" class="form-avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item prop="username" label="用户名">
                        <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
                    </el-form-item>
                    <el-form-item prop="password" label="密码" v-if="dataForm.id == null">
                        <el-input v-model="dataForm.password" placeholder="密码" show-password clearable></el-input>
                    </el-form-item>
                    <el-form-item prop="password_again" label="再一遍" v-if="dataForm.id == null">
                        <el-input v-model="dataForm.password_again" placeholder="密码" show-password clearable></el-input>
                    </el-form-item>
                    <el-form-item prop="nickname" label="昵称">
                        <el-input v-model="dataForm.nickname" placeholder="昵称"></el-input>
                    </el-form-item>
                    <el-form-item prop="phone" label="手机号">
                        <el-input v-model="dataForm.phone" placeholder="手机号"></el-input>
                    </el-form-item>
                    <el-form-item prop="email" label="邮箱">
                        <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
                    </el-form-item>
                    <el-form-item prop="roleIdList" label="角色">
                        <el-select v-model="dataForm.roleIdList" placeholder="请选择" multiple style="width: 100%;">
                            <el-option
                                    v-for="item in roleList"
                                    :key="item.id"
                                    :label="item.nameZh"
                                    :value="item.id"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="enabledFlag" label="账号状态">
                        <el-switch
                                v-model="dataForm.enabledFlag"
                                active-text="启用"
                                inactive-text="禁用">
                        </el-switch>
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
    import userApi from '@/api/user'
    import roleApi from '@/api/role'
    import fileApi from '@/api/file'

    export default {
        name: 'SystemUser',
        components: {},
        data() {
            const validatePasswordAgain = (rule, value, callback) => {
                if (value !== this.dataForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            const validatePhone = (rule, value, callback) => {
                if (!value) {
                    callback();
                }
                const phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/;
                if (!phoneReg.test(value)) {
                    callback(new Error('手机号格式不正确!'));
                } else {
                    callback();
                }
            };
            const validateEmail = (rule, value, callback) => {
                if (!value) {
                    callback();
                }
                const emailReg = /^\w+@[a-z0-9]+\.[a-z]{2,4}$/;
                if (!emailReg.test(value)) {
                    callback(new Error('邮箱格式不正确!'));
                } else {
                    callback();
                }
            };
            return {
                searchForm: {
                    pageNum: 1,  // 页码
                    pageSize: 10,  // 数量
                    username: '',
                },
                searchRule: {
                    username: [
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                },
                tableData: [], // 表格数据
                totalSize: 0, // 表格数据总条数

                // dialog部分
                uploadUrl: fileApi.upload,
                token: {
                    Authorization: `${localStorage.token}` || ''
                },
                dialog: {
                    show: false
                },
                dataForm: {
                    avatar: '',
                    username: '',
                    password: '',
                    password_again: '',
                    nickname: '',
                    phone: '',
                    email: '',
                    roleIdList: [],
                    enabledFlag: true
                },
                dataRule: {
                    // avatar: [
                    //     {required: true, message: "不能为空！", trigger: "change"}
                    // ],
                    username: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    password: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    password_again: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {validator: validatePasswordAgain, trigger: 'change'}
                    ],
                    nickname: [
                        {required: true, message: "不能为空！", trigger: "change"},
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'}
                    ],
                    phone: [
                        {validator: validatePhone, trigger: 'change'}
                    ],
                    email: [
                        {max: 30, message: '长度不能超过 30 个字符', trigger: 'change'},
                        {validator: validateEmail, trigger: 'change'}
                    ],
                    roleIdList: [
                        {required: true, message: "不能为空！", trigger: "change"}
                    ],
                },
                roleList: [],
            }
        },
        created() {
            this.loadData();
            this.getAllRole();
        },
        methods: {
            // 获取表格数据
            loadData() {
                userApi.list(this.searchForm).then(res => {
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
            // 获取所有角色数据
            getAllRole() {
                roleApi.getAllRole().then(res => {
                    this.roleList = res.data.data;
                }).catch(err => {
                })
            },
            // 文件上传成功
            handleAvatarSuccess(res, file) {
                if (res.status === 200) {
                    this.dataForm.avatar = res.data;
                    this.$nextTick(() => {
                        this.$refs['dataForm'].clearValidate('avatar');
                    })
                } else {
                    this.$msg.error(res.msg);
                }
            },
            // 文件上传失败
            handleAvatarError(err, file, fileList) {
                let errJson = err.toString().replace("Error: ", "");
                let errObj = JSON.parse(errJson);
                this.$msg.error(errObj.msg);
            },
            // 文件上传前的文件校验
            beforeAvatarUpload(file) {
                return true;
            },
            // 添加
            handleAdd() {
                this.dataForm.id = null;
                this.dataForm = {
                    avatar: '',
                    username: '',
                    password: '',
                    password_again: '',
                    nickname: '',
                    phone: '',
                    email: '',
                    roleIdList: [],
                    enabledFlag: true
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
                    avatar: row.avatar,
                    username: row.username,
                    nickname: row.nickname,
                    phone: row.phone,
                    email: row.email,
                    roleIdList: [],
                    enabledFlag: row.enabledFlag
                };
                row.roles.forEach(item => {
                    this.dataForm.roleIdList.push(item.id);
                });
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
                    userApi.delete([row.id]).then(res => {
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
                        userApi.update(this.dataForm).then(res => {
                            if (res.data.status === 200) {
                                this.dialog.show = false;
                                this.loadData();
                            }
                        }).catch(err => {
                        });
                    } else {
                        // 新增
                        userApi.save(this.dataForm).then(res => {
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