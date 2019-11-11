/*
 Navicat Premium Data Transfer

 Source Server         : 120.78.211.28
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 120.78.211.28:33306
 Source Schema         : fighting

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 11/11/2019 10:39:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `tags` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属标签(#id#)便于查询',
  `discuss` int(10) NULL DEFAULT 0 COMMENT '评论数',
  `view` int(10) NULL DEFAULT 0 COMMENT '浏览数',
  `draft_flag` bit(1) NULL DEFAULT NULL COMMENT '是否草稿（0：不是   1：是）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(16) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(16) NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '数据有效位（0：有效   1：删除）',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article
-- ----------------------------
INSERT INTO `blog_article` VALUES (1, '个人博客上线', '## 2019年11月10日（天气：🌤）\n### 我的个人博客基本功能实现，完成了部署~\n### 然而现在我还不会用markdown，有点尴尬。\n### 但是今天也算一个里程碑把，记录一下。。。', '#6#', 0, 0, b'0', '2019-11-10 21:54:36', 1, '2019-11-10 21:56:39', 1, b'0', NULL);

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` bigint(16) NULL DEFAULT NULL COMMENT '文章id',
  `tag_id` bigint(16) NULL DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客文章标签关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article_tag
-- ----------------------------
INSERT INTO `blog_article_tag` VALUES (1, 1, 2);

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(16) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(16) NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '数据有效位（0：有效   1：删除）',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES (1, '测试', 1, '2019-11-08 16:19:41', 1, '2019-11-08 16:19:47', 1, b'0', NULL);
INSERT INTO `blog_tag` VALUES (2, '个人记事', 2, '2019-11-10 21:51:50', 1, NULL, NULL, b'0', NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限url（正则模式）',
  `method` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求类型（GET   POST   PUT   DELETE）',
  `path` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端跳转URL',
  `component` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单组件',
  `parent_id` bigint(16) NULL DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单类型（0：目录   1：菜单   2：权限）',
  `external_link` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否外部链接（0：不是   1：是）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(16) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(16) NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '数据有效位（0：有效   1：删除）',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '公共权限', '', '', '', '', 0, '', 0, '0', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (10, '刷新菜单权限', '/system/menu/refreshPermission', 'GET', '', '', 1, '', 1, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (11, '获取菜单树', '/system/menu/getMenuTree', 'GET', '', '', 1, '', 2, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (12, '上传文件', '/file/upload', 'POST', '', '', 1, '', 3, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1000, '系统配置', '', '', '/system', 'Index', 0, 'el-icon-s-tools', 1, '0', b'0', '2019-11-07 10:10:10', 0, '2019-11-07 16:49:29', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1100, '用户管理', '', '', 'user', 'SystemUser', 1000, 'el-icon-s-custom', 1, '1', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1101, '用户列表', '/system/user', 'GET', '', '', 1100, '', 1, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1102, '新增用户', '/system/user', 'POST', '', '', 1100, '', 2, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1103, '修改用户', '/system/user', 'PUT', '', '', 1100, '', 3, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1104, '删除用户', '/system/user', 'DELETE', '', '', 1100, '', 4, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1105, '获取所有角色', '/system/role/getAllRole', 'GET', '', '', 1100, '', 5, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1200, '角色管理', '', '', 'role', 'SystemRole', 1000, 'el-icon-s-promotion', 2, '1', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1201, '角色列表', '/system/role', 'GET', '', '', 1200, '', 1, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1202, '新增角色', '/system/role', 'POST', '', '', 1200, '', 2, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1203, '修改角色', '/system/role', 'PUT', '', '', 1200, '', 3, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1204, '删除角色', '/system/role', 'DELETE', '', '', 1200, '', 4, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1206, '获取角色权限', '/system/role/getRoleMenuIdList', 'GET', '', '', 1200, '', 5, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1300, '菜单管理', '', '', 'menu', 'SystemMenu', 1000, 'el-icon-s-operation', 3, '1', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1301, '菜单树列表', '/system/menu', 'GET', '', ' ', 1300, ' ', 1, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1302, '新增菜单', '/system/menu', 'POST', '', '', 1300, '', 2, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1303, '修改菜单', '/system/menu', 'PUT', '', '', 1300, '', 3, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1304, '删除菜单', '/system/menu', 'DELETE', '', '', 1300, '', 4, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (1305, '获取所有菜单树', '/system/menu/getAllMenuTree', 'GET', '', '', 1300, '', 5, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2000, '我的博客', '', '', '/blog', 'Index', 0, 'el-icon-edit-outline', 2, '0', b'0', '2019-11-07 16:47:34', 1, '2019-11-07 17:08:22', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2100, '文章管理', '', '', 'article', 'BlogArticle', 2000, 'el-icon-document', 1, '1', b'0', '2019-11-07 17:42:13', 1, '2019-11-08 09:12:47', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2101, '文章列表', '/blog/article', 'GET', '', '', 2100, '', 1, '2', b'0', '2019-11-08 09:13:37', 1, '2019-11-08 09:14:05', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2102, '新增文章', '/blog/article', 'POST', '', '', 2100, '', 2, '2', b'0', '2019-11-08 09:14:36', 1, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2103, '修改文章', '/blog/article', 'PUT', '', '', 2100, '', 3, '2', b'0', '2019-11-08 09:14:48', 1, '2019-11-08 09:14:59', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2104, '删除文章', '/blog/article', 'DELETE', '', '', 2100, '', 4, '2', b'0', '2019-11-08 09:15:13', 1, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2105, '获取全部标签', '/blog/tag/getAllTag', 'GET', '', '', 2100, '', 5, '2', b'0', '2019-11-08 17:30:10', 1, '2019-11-08 17:30:27', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2200, '标签管理', '', '', 'tag', 'BlogTag', 2000, 'el-icon-collection-tag', 2, '1', b'0', '2019-11-08 16:12:17', 1, '2019-11-08 16:12:26', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2201, '标签列表', '/blog/tag', 'GET', '', '', 2200, '', 1, '2', b'0', '2019-11-08 16:15:58', 1, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2202, '新增标签', '/blog/tag', 'POST', '', '', 2200, '', 2, '2', b'0', '2019-11-08 16:16:23', 1, '2019-11-08 16:16:30', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2203, '修改标签', '/blog/tag', 'PUT', '', '', 2200, '', 3, '2', b'0', '2019-11-08 16:16:57', 1, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2204, '删除标签', '/blog/tag', 'DELETE', '', '', 2200, '', 4, '2', b'0', '2019-11-08 16:17:19', 1, NULL, NULL, b'0', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name_en` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色英文名称',
  `name_zh` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色中文名称',
  `role_desc` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色介绍',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(16) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(16) NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '数据有效位（0：有效   1：删除）',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_ADMIN', '管理员', '最高权限', '2019-11-07 10:10:10', 0, '2019-11-11 10:28:47', 1, b'0', NULL);
INSERT INTO `sys_role` VALUES (2, 'ROLE_TEST', '测试', '测试角色', '2019-11-07 10:10:10', 0, '2019-11-11 10:17:09', 1, b'0', NULL);
INSERT INTO `sys_role` VALUES (3, 'ROLE_GUEST', '游客', '游客角色', '2019-11-07 10:10:10', 0, '2019-11-11 10:37:54', 1, b'0', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(16) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(16) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10036 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (10000, 2, 1);
INSERT INTO `sys_role_menu` VALUES (10001, 2, 1000);
INSERT INTO `sys_role_menu` VALUES (10002, 2, 11);
INSERT INTO `sys_role_menu` VALUES (10003, 2, 1100);
INSERT INTO `sys_role_menu` VALUES (10004, 2, 1101);
INSERT INTO `sys_role_menu` VALUES (10005, 2, 1200);
INSERT INTO `sys_role_menu` VALUES (10006, 2, 2000);
INSERT INTO `sys_role_menu` VALUES (10007, 2, 1105);
INSERT INTO `sys_role_menu` VALUES (10008, 2, 1201);
INSERT INTO `sys_role_menu` VALUES (10009, 2, 1300);
INSERT INTO `sys_role_menu` VALUES (10010, 2, 2100);
INSERT INTO `sys_role_menu` VALUES (10011, 2, 1301);
INSERT INTO `sys_role_menu` VALUES (10012, 2, 2101);
INSERT INTO `sys_role_menu` VALUES (10013, 2, 1206);
INSERT INTO `sys_role_menu` VALUES (10014, 2, 2200);
INSERT INTO `sys_role_menu` VALUES (10015, 2, 1305);
INSERT INTO `sys_role_menu` VALUES (10016, 2, 2105);
INSERT INTO `sys_role_menu` VALUES (10017, 2, 2201);
INSERT INTO `sys_role_menu` VALUES (10018, 3, 1);
INSERT INTO `sys_role_menu` VALUES (10019, 3, 1000);
INSERT INTO `sys_role_menu` VALUES (10020, 3, 11);
INSERT INTO `sys_role_menu` VALUES (10021, 3, 1100);
INSERT INTO `sys_role_menu` VALUES (10022, 3, 1101);
INSERT INTO `sys_role_menu` VALUES (10023, 3, 1200);
INSERT INTO `sys_role_menu` VALUES (10024, 3, 2000);
INSERT INTO `sys_role_menu` VALUES (10025, 3, 1105);
INSERT INTO `sys_role_menu` VALUES (10026, 3, 1201);
INSERT INTO `sys_role_menu` VALUES (10027, 3, 1300);
INSERT INTO `sys_role_menu` VALUES (10028, 3, 2100);
INSERT INTO `sys_role_menu` VALUES (10029, 3, 1301);
INSERT INTO `sys_role_menu` VALUES (10030, 3, 2101);
INSERT INTO `sys_role_menu` VALUES (10031, 3, 1206);
INSERT INTO `sys_role_menu` VALUES (10032, 3, 2200);
INSERT INTO `sys_role_menu` VALUES (10033, 3, 1305);
INSERT INTO `sys_role_menu` VALUES (10034, 3, 2105);
INSERT INTO `sys_role_menu` VALUES (10035, 3, 2201);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_id` bigint(16) NULL DEFAULT NULL COMMENT '部门id（备用）',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `enabled_flag` bit(1) NULL DEFAULT NULL COMMENT '账号状态（0：禁用   1：启用）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(16) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(16) NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '数据有效位（0：有效   1：删除）',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, NULL, 'fighting', '$2a$10$9dlVPoSWlIe2Qz3bmwLWYuj72APAegn.PRdCO7RjQkVJB8B3DyAQ6', '奋斗把，少年!', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', '', '821863269@qq.com', b'1', '2019-11-07 10:10:10', 0, '2019-11-07 14:19:52', 1, b'0', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg');
INSERT INTO `sys_user` VALUES (2, NULL, 'test', '$2a$10$wDae9lF2VK/9IexLtNYuWeVtQUBXQUdY0d1nRVDgXsJEObEg5SdNW', '测试', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', '', '821863269@qq.com', b'1', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg');
INSERT INTO `sys_user` VALUES (3, NULL, 'guest', '$2a$10$j9iOKR6IL4WtZZ0xFNse1eaeXc9DRz9zpZOzCjIZNXMceqZBOqV0u', '游客', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', '', '821863269@qq.com', b'1', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(16) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(16) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3, 3);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bigint` bigint(16) NULL DEFAULT NULL COMMENT '大数值',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `date` date NULL DEFAULT NULL COMMENT '日期',
  `datetime` datetime(0) NULL DEFAULT NULL COMMENT '具体时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(16) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(16) NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '数据有效位（0：有效   1：删除）',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '测试表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
