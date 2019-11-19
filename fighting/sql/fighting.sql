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

 Date: 19/11/2019 13:57:28
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
) ENGINE = InnoDB AUTO_INCREMENT = 10045 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article
-- ----------------------------
INSERT INTO `blog_article` VALUES (1, '个人博客上线', '## 2019年11月10日（天气：🌤）\n### 我的个人博客基本功能实现，完成了部署~\n### 然而现在我还不会用markdown，有点尴尬。\n### 但是今天也算一个里程碑把，记录一下。。。', '#2#', 0, 3, b'0', '2019-11-10 21:54:36', 1, '2019-11-15 17:32:56', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10000, 'Markdown基本语法', 'Markdown是一种纯文本格式的标记语言。通过简单的标记语法，它可以使普通文本内容具有一定的格式。\n\n# 一、标题\n在想要设置为标题的文字前面加#来表示\n一个#是一级标题，二个#是二级标题，以此类推。支持六级标题\n==注：标准语法一般在#后跟个空格再写文字==\n实例：\n```language\n# 这是一级标题\n## 这是二级标题\n### 这是三级标题\n#### 这是四级标题\n##### 这是五级标题\n###### 这是六级标题\n```\n\n# 二、字体\n\n- 加粗\n	- 要加粗的文字左右分别用两个*号包起来\n- 斜体\n	- 要倾斜的文字左右分别用一个*号包起来\n- 斜体加粗\n	- 要倾斜和加粗的文字左右分别用三个*号包起来\n- 删除线\n	- 要加删除线的文字左右分别用两个~~号包起来\n\n示例：\n```language\n**这是加粗的文字**\n*这是倾斜的文字*`\n***这是斜体加粗的文字***\n~~这是加删除线的文字~~\n```\n效果如下：\n**这是加粗的文字**\n*这是倾斜的文字*`\n***这是斜体加粗的文字***\n~~这是加删除线的文字~~\n\n# 三、引用\n在引用的文字前加>即可。引用也可以嵌套，如加两个>>三个>>>\nn个...\n貌似可以一直加下去，但没神马卵用\n\n示例：\n```language\n>这是引用的内容\n>>这是引用的内容\n>>>>>>>>>>这是引用的内容\n```\n效果如下：\n>这是引用的内容\n>>这是引用的内容\n>>>>>>>>>>这是引用的内容\n\n# 四、分割线\n三个或者三个以上的 - 或者 * 都可以。\n示例：\n```language\n---\n----\n***\n*****\n```\n效果如下：\n---\n----\n***\n*****\n\n# 五、图片\n语法：\n```language\n![图片alt](图片地址 \'\'图片title\'\')\n\n图片alt就是显示在图片下面的文字，相当于对图片内容的解释。\n图片title是图片的标题，当鼠标移到图片上时显示的内容。title可加可不加\n```\n示例：\n```language\n![blockchain](https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/\nu=702257389,1274025419&fm=27&gp=0.jpg \"区块链\")\n```\n效果如下：\n![blockchain](https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=702257389,1274025419&fm=27&gp=0.jpg \"区块链\")\n\n# 六、超链接\n语法：\n```language\n[超链接名](超链接地址 \"超链接title\")\ntitle可加可不加\n```\n示例：\n```language\n[百度](http://baidu.com)\n```\n[百度](http://baidu.com)\n\n# 七、列表\n```language\n无序列表语法：\n无序列表用 - + * 任何一种都可以\n```\n示例：\n```language\n- 列表内容\n+ 列表内容\n* 列表内容\n\n注意：- + * 跟内容之间都要有一个空格\n```\n效果如下：\n- 列表内容\n+ 列表内容\n* 列表内容\n\n有序列表语法：\n数字加点\n示例：\n```language\n1.列表内容\n2.列表内容\n3.列表内容\n\n注意：序号跟内容之间要有空格\n```\n效果如下：\n1.列表内容\n2.列表内容\n3.列表内容\n\n==列表嵌套(上一级和下一级之间敲三个空格即可)==\n\n# 八、表格\n语法：\n```language\n表头|表头|表头\n---|:--:|---:\n内容|内容|内容\n内容|内容|内容\n\n第二行分割表头和内容。\n- 有一个就行，为了对齐，多加了几个\n文字默认居左\n-两边加：表示文字居中\n-右边加：表示文字居右\n注：原生的语法两边都要用 | 包起来。此处省略\n```\n示例：\n```language\n姓名|技能|排行\n--|:--:|--:\n刘备|哭|大哥\n关羽|打|二哥\n张飞|骂|三弟\n```\n效果如下：\n姓名|技能|排行\n--|:--:|--:\n刘备|哭|大哥\n关羽|打|二哥\n张飞|骂|三弟\n\n# 九、代码\n语法：\n单行代码：代码之间分别用一个反引号包起来\n```language\n `代码内容`\n```\n代码块：代码之间分别用三个反引号包起来，且两边的反引号单独占一行\n```language\n(```)\n  代码...\n  代码...\n  代码...\n(```)\n```\n>注：为了防止转译，前后三个反引号处加了小括号，实际是没有的。这里只是用来演示，实际中去掉两边小括号即可。\n示例：\n单行代码\n`create database hero;`\n代码块\n```javascript\nfunction fun(){\n     echo \"这是一句非常牛逼的代码\";\n}\nfun();\n```\n\n\n    \n', '#10000#', 0, 2, b'0', '2019-11-13 08:48:31', 1, '2019-11-13 09:27:08', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10001, 'Shrio与JWT的比较', '::: hljs-center\n\n![JWT和Shiro.png](/fileServer/2019-11-15/61e3aebb8cce9d8ade1185025a77472f.png)\n\n:::\n', '#10001#10002#', 0, 2, b'0', '2019-11-15 13:41:54', 1, '2019-11-15 13:44:10', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10009, 'TCP的三次握手（1）', '::: hljs-center\n\n# 传输控制协议TCP的简介\n1. 面向连接的、可靠的、基于字节流的传输层通信协议\n2. 将应用层的数据流分割成报文段并发送给目标节点的TCP层\n3. 数据包都有序号，对方接收到则发送ACK确认，未收到则重传\n4. 使用校验来校验数据在传输过程成是否有误\n\n# TCP报文头\n![image.png](/fileServer/2019-11-15/dbc2efb73c9da01a366b170bca6295fb.png)![image.png](/fileServer/2019-11-15/bc882d3315aada49a113bbc0de2d1358.png)\n\n:::\n\n\n16位端口号：标示该段报文来自哪里（源端口）以及要传给哪个上层协议或应用程序（目的端口）。进行tcp通信时，一般client是通过系统自动选择的临时端口号，而服务器一般是使用知名服务端口号或者自己指定的端口号。\n\n32位序号：表示一次tcp通信过程（从建立连接到断开）过程中某一次传输方向上的字节流的每个字节的编号。假定主机A和B进行tcp通信，A传送给B一个tcp报文段中，序号值被系统初始化为某一个随机值ISN，那么在该传输方向上（从A到B），后续的所有tcp报文断中的序号值都会被设定为ISN加上该报文段所携带数据的第一个字节在整个字节流中的偏移。例如某个TCP报文段传送的数据是字节流中的第1025~2048字节，那么该报文段的序号值就是ISN+1025。\n\n32位确认号：用作对另一方发送的tcp报文段的响应。其值是收到对方的tcp报文段的序号值＋1。假定主机A和B进行tcp通信，那么A发出的tcp报文段不但带有自己的序号，也包含了对B发送来的tcp报文段的确认号。反之也一样。\n\n4位头部长度：表示tcp头部有多少个32bit字（4字节），因为4位最大值是15，所以最多有15个32bit，也就是60个字节是最大的tcp头部长度。\n\n6位标志位：\nURG：紧急指针是否有效\nACK：表示确认好是否有效，携带ack标志的报文段也称确认报文段\nPSH：提示接收端应用程序应该立即从tcp接受缓冲区中读走数据，为后续接收的数据让出空间\nRST：表示要求对方重建连接。带RST标志的tcp报文段也叫复位报文段\nSYN：表示建立一个连接，携带SYN的tcp报文段为同步报文段\nFIN标志：表示告知对方本端要关闭连接了。\n\n16为窗口大小：是TCP流量控制的一个手段，这里说的窗口是指接收通告窗口，它告诉对方本端的tcp接收缓冲区还能容纳多少字节的数据，这样对方就可以控制发送数据的速度。\n\n16为校验和：由发送端填充，接收端对tcp报文段执行CRC算法以检验TCP报文段在传输过程中是否损坏。注意这个校验不仅包括tcp头部，也包括数据部分。这也是tcp可靠传输的一个重要保障。\n\n16位紧急指针：是一个正的偏移量。它和序号字段的值相加表示最后一个紧急数据的下一字节的序号。因此这个字段是紧急指针相对当前序号的偏移量。不妨称之为紧急便宜，发送紧急数据时会用到这个。\n\nTCP头部选项：最后一个选项字段是可变长的可选信息，最多包含40字节的数据。典型的tcp头部选项结构：\n::: hljs-center\n\n![image.png](/fileServer/2019-11-15/57c87bba69c52c8ec08f00d639febbf1.png)\nkind表示选项的类型，length表示选项的长度（全部），info表示选项的具体内容，常见的头部选项有以下7种：\n![image.png](/fileServer/2019-11-15/d9e6523f20ed105b081a0e25c5ad9ef3.png)\n每一个kind值表示一个类型，每个值得含义：\n![image.png](/fileServer/2019-11-15/e80d8a3b1bcdf512752e8baa897c1fa8.png)\n\n:::\n\n\n', '#10003#', 0, 2, b'0', '2019-11-15 22:18:52', 1, '2019-11-15 22:51:34', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10010, 'TCP的三次握手（2）', '# \"握手”是为了建立连接，TCP三次握手的流程图如下：\n![image.png](/fileServer/2019-11-15/e51a9f8726444a6068c27e7d8ce00c74.png)\n\n在TCP/IP协议中，TCP协议提供可靠的连接服务，采用三次握手建立一个连接。\n\n第一次握手：建立连接时，客户端发送SYN包(syn=j)到服务器，并进入SYN_SEND状态，等待服务器确认；\n\n第二次握手：服务器收到SYN包，必须确认客户的SYN(syn=j+1),同时自己也发送一个SYN包(syn=k)，即SYN+ACK包，此时服务器进入SYN_RECV状态；\n\n第三次握手：客户端收到服务器的SYN+ACK包，向服务器发送确认包ACK(ack=k+1),此包发送完毕，客户端和服务器进入ESTABLISHED状态，完成三次握手。\n\n# 为什么需要三次握手才能建立起连接？？？\n\n为了初始化Sequence Number的初始值\n\n# 首次握手的隐患---SYN超时\n## 问题起因分析\n\nServer收到Client的SYN，回复SYN-ACK的时候未收到ACK确认\n\nServer不断重试知道超时，Linux默认等待63秒才断开连接（每次重试时间都翻倍1+2+4+8+16+32=63）\n\n## 服务器有可能受到这种SYN Flood攻击\n## 那应该如何针对SYN Flood进行防护措施\nSYN队列满了，通过tcp_syncookies参数回发SYN Cookie\n\n若为正常连接则Client会回发SYN Cookie，直接建立连接\n\n# 建立连接后，Client出现故障怎么办？？？\n### 保活机制\n\n向对方发送保活探测报文，如果未收到响应则继续发送\n\n尝试次数到达保活探测数仍未收到响应则中断连接', '#10003#', 0, 1, b'0', '2019-11-15 22:55:33', 1, '2019-11-15 23:05:50', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10011, 'TCP的四次挥手', '::: hljs-center\n\n# \"挥手\"是为了终止连接，TCP四次挥手的流程图如下：\n![image.png](/fileServer/2019-11-15/8db699955ae52a996b48b4014311c9c6.png)\n\n:::\n\n## TCP采用四次挥手释放连接\n\n第一次挥手：Client发送一个FIN，用来关闭Client到Server的数据传送，Client进入FIN_WAIT_状态；\n\n第二次挥手：Server收到FIN后，发送一个ACK给Client，确认序号为收到序号+1（与SYN相同，一个FIN占用一个序号），Server进入CLOSE_WAIT状态；\n\n第三次挥手：Server发送一个FIN，用来关闭Server到Client的数据传送，Server进入LAST_ACK状态；\n\n第四次挥手：Client收到FIN后，Client进入TIIME_WAIT状态，接着发送一个ACK给Server，确认序号为收到序号+1，Server进入CLOSED状态，完成四次挥手。\n\n# 为什么会有TIME_WAIT状态？？？\n## 原因\n确保有足够的时间让对方收到ACK包\n避免新旧连接混淆\n\n# 为什么需要四次挥手才能断开连接？？？\n## 因为全双工，发送放和接收方都需要FIN报文和ACK报文\n\n# 服务器出现大量CLOSE_WAIT状态的原因\n对方关闭socket连接，我方忙于读或写，没有及时关闭连接\n\n检查代码，特别是释放资源的代码\n\n检查配置，特别是处理请求的线程配置\n\n', '#10003#', 0, 2, b'0', '2019-11-15 23:58:14', 1, '2019-11-16 00:04:55', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10012, 'TCP和UDP的区别', '# UDP简介\n## UDP的特点\n1. 面向非连接\n2. 不维护连接状态，支持同时向多个客户端传输相同的消息\n3. 数据包报文头只有8个字节，额外开销较小\n4. 吞吐量只受限于数据生成速率，传输速率以及机器性能\n5. 尽最大努力交付，不保证可靠交付，不需要维持复杂的链接状态表\n6. 面向报文，不对应用程序提交的报文信息进行拆分或者合并\n\n# TCP和UDP的区别\n## 总结\n1. 面向链接 vs 无连接\n2. 可靠性\n3. 有序性（TCP有序，有序列号）\n4. 速度（TCP速度慢，需要创建连接以及很多额外操作，UDP则不需要）\n5. 量级（TCP重量级（头20个字节） UDP轻量级（头8个字节））\n\n\n\n', '#10003#', 0, 1, b'0', '2019-11-16 00:11:46', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10013, 'TCP滑动窗口', '# RTT和RTO\n\nRTT 发送一个数据包到收到对应的ACK，所花费的时间\n\nRTO 重传时间间隔\n\n# TCP的滑动窗口\n\n## TCP使用滑动窗口做流量控制和乱序重排\n\n保证TCP的可靠性\n\n保证TCP的流控特性\n\n## 窗口数据的计算过程\n\n![image.png](/fileServer/2019-11-16/663a2b275692e783b38e35d7ed625ba4.png)\n\n公式1：AdvertisedWindow = MaxRcvBuffer - （LastByteRcvd - LastByteRead）\n\n公式2：EffectiveWindow = AdvertisedWindow - （LastByteSent - LastByteAcked）\n\n## TCP会话的发送方\n![image.png](/fileServer/2019-11-16/f9d6d5ee53afd6efff564677350914b2.png)\n\n## TCP会话的接收方\n![image.png](/fileServer/2019-11-16/a03414c973fcdb428c498f4c738304ee.png)\n\n', '#10003#', 0, 3, b'0', '2019-11-16 00:19:44', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10014, '学习计算机网络基础感悟', '## 2019年11月16日凌晨（天气：🌤）\n### 看了下计算机网络基础的资料，大部分都是理论上的内容\n### 听了也不是很了解，不是很明白\n### 有机会可以继续了解一下\n### 睡觉，睡觉。。。', '#2#', 0, 1, b'0', '2019-11-16 00:22:51', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10015, 'HTTP协议', '# HTTP 简介\n## 超文本传输协议HTTP主要特点\n\n1. 支持客户端/服务器模式\n2. 简单快速\n3. 灵活\n4. 无连接\n5. 无状态 \n\n## HTTP请求结构\n![image.png](/fileServer/2019-11-16/7967b315979ae454e661115edaf4a3e2.png)\n==换行符(\\r\\n)==\n\n如下图：\n![image.png](/fileServer/2019-11-16/1c88b12c2d71e9c8cfbb6612e7e18e7d.png)\n\n## HTTP响应报文\n![image.png](/fileServer/2019-11-16/c6ebd9d2aaf9bcca8fc73bfda399aecd.png)\n\n如下图：\n![image.png](/fileServer/2019-11-16/2f3285d80e8bad818cf8bb134abe1cce.png)\n\n# 请求/响应的步骤\n1. 客户端连接到Web服务器\n2. 发送HTTP请求\n3. 服务器接受请求并返回HTTP响应\n4. 释放连接TCP连接\n5. 客户端浏览器解析HTML内容\n\n# 在浏览器地址栏键入URL，按下回车之后尽力的流程\n## 答案\n1. DNS解析（从浏览器缓存，计算机缓存。。。找到就返回对应IP）\n2. TCP连接\n3. 返送HTTP请求\n4. 服务器处理请求并返回HTTP报文\n5. 浏览器解析渲染页面\n6. 连接结束（4次挥手）\n\n# HTTP常见的状态码\n## 五种可能的取值\n1. 1xx:提示信息---表示请求已接收，继续处理\n2. 2xx:成功---表示请求已被成功接收、理解、接受\n3. 3xx:重定向---要完成请求必须进行更进一步的操作\n4. 4xx:客户端错误---请求有语法错误或请求无法实现\n5. 5xx:服务器错误---服务器未能实现合法的请求\n\n## 具体状态码\n- 200 OK: 正常返回信息\n- 400 Bad Request: 客户端请求有语法错误，不能被服务器所理解\n- 401 Unauthorized: 请求未经授权，这个状态代码必须和WWW-Authenticate报头域一起使用\n- 403 Forbidden: 服务器收到请求，但是拒绝服务\n- 404 Not Found: 请求资源不存在，eg：输入了错误的URL\n- 500 Internal Server Error: 服务器发生不可预期的错误\n- 503 Server Unavailable: 服务器当前不能处理客户端的请求，一段时间后可能恢复正常\n\n# GET请求和POST请求的区别\n## 从三个层面来解答\n- Http报文层面：GET请求信息放在URL，POST放在报文体中\n- 从数据库层面：CET符合幂等性和安全性，POST不符合\n- 其他层面：GET可以被缓存，被存储，而POST不行\n\n# Cookie和Session\n## Cookie简介\n- 是由服务器发给客户端的特殊信息，以文本的形式存放在客户端\n- 客户端再次请求的时候，会把Cookie回发\n- 服务器接收到后，会解析Cookie生成与客户端相对应的内容\n\n## Cookie的设置以及发送过程\n![image.png](/fileServer/2019-11-16/20b5cb3d274d468728f972bad0994b1e.png)\n\n## Session简介\n- 服务器端的机制，在服务器上保存信息\n- 解析客户端请求并操作session id，按需保存状态信息\n\n## Seesion的实现方式\n- 使用Cookie来实现\n- 使用URL回写来实现\n\n## Cookie和Session的区别\n- Cookie数据存放在客户端的浏览器上，Session数据放在服务器上\n- Session相对于Cookie更安全\n- 若考虑减轻服务器负担，应当使用Cookie\n', '#10003#', 0, 1, b'0', '2019-11-16 09:12:56', 1, '2019-11-16 09:35:14', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10016, 'HTTP和HTTPS', '# HTTPS简介\n\n![image.png](/fileServer/2019-11-16/966bbdb8235432106375574bd653e463.png)\n\n## SSL(Security Socket Layer, 安全套接层)\n- 为网络通信提供安全及数据完整性的一种安全协议\n- 是操作系统对外的API，SSL3.0后更名为TLS\n- 采用身份验证和数据加密保证网络通信的安全和数据的完整性\n\n## 加密的方式\n- 对称加密：加密和解密都使用同一个密钥\n- 非对称加密：加密使用的密钥和解密使用的密钥是不同的\n- 哈希算法：将任意长度的信息转换为固定长度的值，算法不可逆\n- 数字签名：证明某个消息或者文件是某人发出/认同的\n\n## HTTPS数据传输流程\n- 浏览器将支持的加密算法信息发送给服务器\n- 服务器选择一套浏览器支持的加密算法，以证书的形式回发给浏览器\n- 浏览器验证证书合法性，并结合证书公钥加密信息发送给浏览器\n- 服务器使用密钥解密信息，验证哈希，加密响应消息回发浏览器\n- 浏览器解密响应消息，并对消息进行验真，之后进行加密交互数据\n\n# HTTP和HTTPS的区别\n- HTTPS需要到CA申请证书，HTTP不需要\n- HTTPS密文传输，HTTP明文传输\n- 连接方式不同，HTTPS默认是443端口，HTTP使用80端口\n- HTTPS=HTTP + 加密 + 认证 + 完整性保护，较HTTP更安全\n\n# HTTPS真的很安全吗？？？\n- 浏览器默认填充http://,请求需要进行跳转，有被劫持的风险\n- 可以使用HSTS(HTTP Strict Transport Security)优化\n- \n\n', '#10003#', 0, 1, b'0', '2019-11-16 09:36:35', 1, '2019-11-16 10:00:58', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10017, 'Socket', '# Socket是对TCP/IP协议的抽象，是操作系统对外开发的接口（方便程序员使用）\n![image.png](/fileServer/2019-11-16/9222403670a0edeaf8c2eb32f2d0b2c6.png)\n\n## Socket通信流程\n![image.png](/fileServer/2019-11-16/35cbf663d3ae398961596d54b77bbd45.png)\n\n## 实战demo\n编写一个网络应用程序，有客户端与服务器端，客户端向服务器发送一个字符串，服务器收到该字符串后将其打印到命令行上，然后向客户端返回该字符串的长度，最后，客户端输出服务器端返回该字符串的长度，分别用TCP和UDP两种方式去实现。\n\n## TCP\n```java\n// TCPServer\npublic static void main(String[] args) throws IOException {\n    ServerSocket serverSocket = new ServerSocket(65000);\n    while (true) {\n        Socket socket = serverSocket.accept();\n        new LengthCalculator(socket).start();\n    }\n}	\n\n// TCPClient\npublic static void main(String[] args) throws IOException {\n    Socket socket = new Socket(\"127.0.0.1\", 65000);\n    InputStream inputStream = socket.getInputStream();\n    OutputStream outputStream = socket.getOutputStream();\n    outputStream.write(\"hello world\".getBytes());\n    int length = 0;\n    byte[] buff = new byte[1024];\n    length = inputStream.read(buff);\n    String result = new String(buff, 0, length);\n    System.out.println(result);\n    outputStream.close();\n    inputStream.close();\n    socket.close();\n}\n\n// 消息处理类\npublic class LengthCalculator extends Thread {\n\n    private Socket socket;\n\n    public LengthCalculator(Socket socket) {\n        this.socket = socket;\n    }\n\n    @Override\n    public void run() {\n        try {\n            // 输出流\n            OutputStream outputStream = socket.getOutputStream();\n            // 输入流\n            InputStream inputStream = socket.getInputStream();\n            // 定义读取的长度\n            int length = 0;\n            // 定义接收内容的字节数组\n            byte[] buff = new byte[1024];\n            length = inputStream.read(buff);\n            String content = new String(buff, 0, length);\n            System.out.println(content);\n            outputStream.write(String.valueOf(length).getBytes());\n            inputStream.close();\n            outputStream.close();\n            socket.close();\n        } catch (IOException e) {\n            e.printStackTrace();\n        }\n    }\n\n}\n```\n\n## UDP\n```java\n// UDPServer\npublic static void main(String[] args) throws IOException {\n    DatagramSocket datagramSocket = new DatagramSocket(65001);\n    byte[] buff = new byte[1024];\n    DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length);\n    // 接收客户端发送过来的内容，并将内容封装到DatagramPacket对象中去\n    datagramSocket.receive(datagramPacket);\n    // 解析客户端发来的数据包\n    byte[] data = datagramPacket.getData();\n    String content = new String(data, 0, datagramPacket.getLength());\n    System.out.println(content);\n    // 构造返回数据包\n    byte[] sendBuff = String.valueOf(content.length()).getBytes();\n    DatagramPacket packetToClient = new DatagramPacket(sendBuff, sendBuff.length,\n            datagramPacket.getAddress(), datagramPacket.getPort());\n    // 返回给客户端\n    datagramSocket.send(packetToClient);\n}\n\n// UDPClient\npublic static void main(String[] args) throws Exception {\n    DatagramSocket socket = new DatagramSocket();\n    byte[] buff = \"hello world\".getBytes();\n    InetAddress address = InetAddress.getByName(\"127.0.0.1\");\n    // 封装发送数据包\n    DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length, address, 65001);\n    // 发送数据包\n    socket.send(datagramPacket);\n    // 客户端接收服务器发送过来的数据包\n    byte[] data = new byte[1024];\n    DatagramPacket result = new DatagramPacket(data, data.length);\n    socket.receive(result);\n    String resultContent = new String(data, 0, result.getLength());\n    System.out.println(resultContent);\n}\n```\n\n', '#10003#', 0, 1, b'0', '2019-11-16 10:17:33', 1, '2019-11-16 10:47:27', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10018, '关系型数据库', '# 关系型数据库设计\n![image.png](/fileServer/2019-11-16/d9b760d5ee8eedb5cb09387943c48a8e.png)\n\n# 索引模块\n## 1.为什么要使用索引（灵感来源于字典）\n    快速查询数据\n	\n## 2.什么样的信息能成为索引\n    主键、唯一键以及普通键等\n\n## 3.索引的数据结构\n    \n### 生成索引，建立二叉查找树进行二分查找\n![image.png](/fileServer/2019-11-16/400b5bc5a8424c2b53e896eb70c1d5c5.png)\n\n### 生成索引，建立B-Tree结构进行查找\n![image.png](/fileServer/2019-11-16/dbf4588b0440e70fdabf21daf03893ac.png)\n    B-Tree定义：\n	根节点最少包含两个孩子\n	树中每个节点最多含有m个孩子(m >= 2)\n	除根节点和叶子节点外，其他每个节点至少有ceil(m/2)个孩子\n	所有叶子节点都位于同一层\n![image.png](/fileServer/2019-11-16/887b120b7ff7497c9c48fbf5a118da4f.png)\n\n### 生成索引，建立B+-Tree结构进行查找\n![image.png](/fileServer/2019-11-16/ab376e09630160d3ff29e41a3700a784.png)\n    B+-Tree定义：\n	非叶子节点的子树指针与关键字个数相同\n	非叶子节点的子树指针P[i],指向关键字值[k[i], k[i+1])的子树\n	非叶子节点仅用来索引，数据都保存在叶子节点中	\n	所有叶子节点均有一个链指针指向下一个叶子节点\n    ==结论：B+Tree更适合用来做存储索引\n	B+树的磁盘读写代价更低\n	B+树的查询效率更加稳定\n	B+树更有利于对数据库的扫描==\n\n### 生成索引，建立Hash结构进行查找\n![image.png](/fileServer/2019-11-16/26fbe8e98b30bc8effb10090aa1a0c15.png)\n    缺点：\n	- 仅仅能满足“=”，“IN” 不能使用范围查询\n	- 无法被用来避免数据的排序操作\n	- 不能利用部分索引键查询\n	- 不能避免表扫描\n	- 遇到大量Hash值相等的情况后性能并不一定就会比B-Tree索引高\n\n### BitMap索引是个神器\n![image.png](/fileServer/2019-11-16/d7f2fb34d0c4b3cb298cf25473d3e1df.png)\n\n## 4.密集索引和稀疏索引的区别\n![image.png](/fileServer/2019-11-16/fe1f5a4cd19ab0328e32a4a12b13cee4.png)\n    密集索引文件中的每个搜索码值都对应一个索引值\n    稀疏索引文件只为索引码的某些值建立索引项\n\n密集索引的定义：叶子节点保存的不只是键值，还保存了位于同一行记录里的其他列的信息，由于密集索引决定了表的物理排列顺序，一个表只有一个物理排列顺序，所以一个表只能创建一个密集索引\n\n稀疏索引：叶子节点仅保存了键位信息以及该行数据的地址，有的稀疏索引只保存了键位信息机器主键\n\nmysam存储引擎，不管是主键索引，唯一键索引还是普通索引都是稀疏索引\n\ninnodb存储引擎：有且只有一个密集索引。密集索引的选取规则如下：\n\n若主键被定义，则主键作为密集索引\n如果没有主键被定义，该表的第一个唯一非空索引则作为密集索引\n若不满足以上条件，innodb内部会生成一个隐藏主键（密集索引）\n非主键索引存储相关键位和其对应的主键值，包含两次查找\n\n![image.png](/fileServer/2019-11-16/155fe6d407c009c16c4f702b68511dd8.png)', '#10005#', 0, 2, b'0', '2019-11-16 13:35:36', 1, '2019-11-16 15:45:30', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10019, '如何定位并优化慢查询Sql（Mysql为例）', '# 具体场景具体分析，提出大致思路\n## 根据慢日志定位慢查询sql\n```sql\n-- 查询系统变量\nshow variables like \'%quer%\';\n\n--慢查询的数量\nshow status like \'%slow_queries%\';\n\n--打开慢查询日志\nset global slow_query_log=on;\n\n--设置慢查询时间（这个要断开重新连接下）\nset global long_query_time=1;\n```\n![image.png](/fileServer/2019-11-16/1009cd21c7ab548cf597e66a06e46de8.png)\n\n## 使用explain等工具分析sql\n\nexplain一般放在select前面\nexplain是对sql进行了分析，并没有真正运行\n\n### Explain关键字段（type、extra）\n==id越大，越先执行==\n![image.png](/fileServer/2019-11-16/2e30b6968a22ad78ae18f5bde80df6be.png)\n![image.png](/fileServer/2019-11-16/702fd6b845dc6c987003fd1f096eb624.png)\n\n## 修改sql或者尽量让sql走索引\n```sql\n-- 给表字段添加索引\nalter table 表名 add index idx_字段名(字段名);\n```\n', '#10005#', 0, 1, b'0', '2019-11-16 19:25:18', 1, '2019-11-16 19:33:29', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10020, '联合索引的最左匹配原则的原因', '## 例如KEY `index_area_title`(`area`,`title`)这个索引\n\n在条件为where area = \'...\' and title = \'...\'\n\n或where title = \'...\' and area = \'...\'\n\n或where area = \'...\'都是会走索引的\n\n如果只有where title = \'...\' 是不会走索引的\n\n## 总结\n\n- 最左前缀匹配原则，非常重要的原则，mysql会一直向右匹配知道遇到范围查询(>、<、between、like)就停止匹配，比如a = 3 and b = 4 and d = 6 如果建立(a,b,c,d)顺序的索引，d是用不到索引的，如果建立(a,b,d,c)的索引则都可以用到，a,b,d的顺序可以任意调整。\n- =和in可以乱序，比如a = 1 and b = 2 and c = 3 建立(a,b,c)索引可以任意顺序，mysql的查询优化器会帮你优化成索引可以识别的形式', '#10005#', 0, 1, b'0', '2019-11-16 19:46:21', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10021, '索引是建立得越多越好吗？？？', '# 数据量小的表不需要建立索引，建立会增加额外的索引开销\n# 数据变更需要维护索引，因此更多的索引意味着更多的维护成本\n# 更多的索引意味着也需要更多的空间', '#10005#', 0, 2, b'0', '2019-11-16 19:48:07', 1, '2019-11-16 19:48:24', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10022, '锁模块', '# MyISAM 与 InnoDB关于锁方面的区别是什么？\n\n- MyISAM默认用的是表级锁，不支持行级锁（MyISAM在查询的时候默认会给表上锁，增删改操作会阻塞）\n- InnoDB默认用的是行级锁，也支持表级锁 \n\n```sql\n-- 显示给表加锁\nlock tables 表名 read/write;\n-- 释放锁\nunlock tables;\n```\n\n## 表级锁总结：\n- 在上读锁的时候，其他session可以进行查询，增删改阻塞\n- 在上写锁的时候，其他session的查询阻塞，增删改也阻塞\n- 所以读锁称为共享锁，写锁称为排它锁\n- 如何给select增加排它锁，在查询语句最后加上for update;\n\n## 行级锁总结:\n```sql\n-- 查看事务是否是自动提交\nshow variables like \'autocommit\';\n-- 关闭自动提交(只对当前会话有效)\nset autocommit = 0;\n-- 给查询语句上读锁（lock in share mode）\nselect * from 表名 where id = 3 lock in share mode;\n```\n\n- commit默认自动提交\n- 在上了读锁的时候，其他seesion可以进行查询，增删改阻塞（但是修改非查询的行可以正常执行）\n- 在上写锁的时候，其他session的查询修改的行阻塞（其他行正常），增删改(修改的行)阻塞（其他行正常）\n- 如果查询走的不是索引的时候，此时上的是表级锁，走索引的时候，用的是行级锁\n- 锁的力度越细，代价越大\n\n\n# MyISAM适合的场景\n- 频繁执行全表count的语句(MyISAM有表单保存了具体行数，InnoDB则没有保存，每次都需要去扫描统计)\n- 对数据进行的增删改的频率不高，查询非常频繁\n- 没有事务\n\n# InnoDB适合的场景\n- 数据增删改查都相当频繁\n- 可靠性要求比较高，要求支持事务\n\n# 数据库锁的分类\n- 按锁的粒度划分，可以分为表记锁，行级锁，页级锁\n- 按锁级别划分，可以分为共享锁，排它锁\n- 按加锁方式划分，可以分为自动锁，显示锁\n- 按操作划分，可以分为DML锁，DDL锁\n- 按使用方式划分，分为乐观锁，悲观锁\n\n## 乐观锁的实现（给表字段增加一个version）\n- 数据每更新一次version+1\n- 每次更新的时候比对version和读出来的version是否一致\n- 一致则更新，不一致则说明数据有变动，先读取再重复更新操作\n\n```sql\n-- 1.先读取表的数据，得到Version的值为versionValue\nselect version from 表名 where id = xx;\n-- 例如这里查询出来的version = 0;\n-- 2.每次更新表中的其他字段的时候，为了防止发生冲突\n--   先检查version再做更新，sql如下\nupdate 表名 set 字段=value, version = 0 + 1 where version = 0 and id = xx;\n-- 3.如果这里执行操作后,返回影响行数为1,说明更新成功。\n--   如果返回的更新行数为0，则需要重复1，2步操作\n--   这就是乐观锁的一种实现，再更新时检查数据，而不是一开始就给数据枷锁 \n```\n\n\n\n\n\n\n\n\n\n\n', '#10005#', 0, 1, b'0', '2019-11-16 20:32:43', 1, '2019-11-16 20:35:09', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10024, '数据库事务的四大特性（ACID）', 'ACID，指数据库事务正确执行的四个基本要素的缩写。包含：==原子性（Atomicity）、一致性（Consistency）、隔离性（Isolation）、持久性（Durability）==。一个支持事务（Transaction）的数据库，必需要具有这四种特性，否则在事务过程（Transaction processing）当中无法保证数据的正确性，交易过程极可能达不到交易方的要求。\n\n# ⑴ 原子性（Atomicity）\n第一个原子性，这个是最简单的。说的是一个事物内所有操作共同组成一个原子包，要么全部成功，要么全部失败。这是最基本的特性，保证了因为一些其他因素导致数据库异常，或者宕机。\n	\n# ⑵ 一致性（Consistency）\n原子性中规定方法中的操作都执行或者都不执行，但并没有说要所有操作一起执行（一起更新那就乱套了，要哪个结果？），所以操作的执行也是有先后顺序的，那我们要是在执行一半时查询了数据库，那我们会得到中间的更新的属性？答案是不会的，一致性规定事务提交前后只存在两个状态，提交前的状态和提交后的状态，绝对不会出现中间的状态\n\n# ⑶ 隔离性（Isolation）\n事务的隔离性基于原子性和一致性，每一个事务可以并发执行，但是他们互不干扰，但是也有可能不同的事务会操作同一个资源，这个时候为了保持隔离性会用到锁方案。\n\n# ⑷ 持久性（Durability）\n当一个事务提交了之后那这个数据库状态就发生了改变，哪怕是提交后刚写入一半数据到数据库中，数据库宕机(死机)了，那当你下次重启的时候数据库也会根据提交日志进行回滚，最终将全部的数据写入。', '#10005#', 0, 1, b'0', '2019-11-16 20:57:25', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10025, '事务隔离级别以及各级别下的并发访问问题', '# 事务并发访问引起的问题以及如何避免\n```sql\n-- 查看事务隔离级别\nselect @@tx_isolation;\n-- 设置会话事务隔离级别为：读未提交(会出现脏读)\nset session transaction isolation level read uncommitted;\n-- 开启事务\nstart transaction;\n-- 回滚\nrollback;\n-- 提交事务\ncommit;\n```\n## 更新丢失---mysql所有事务隔离级别在数据库层面上均可避免\n## 脏读---READ-COMMITTED事务隔离级别以上可避免\n## 不可重复读---REPEATABLE-READ事务隔离级别以上可避免(事务多次读取同一数据，结果不一致，因为其他事务修改了数据)\n## 幻读---SERIALIZABLE事务隔离级别可避免(事务A查询只有3条，准备对这三条进行更新，事务B执行了插入操作，事务A更新了4条，此类情况为幻读)\n\n![image.png](/fileServer/2019-11-16/b9642f065734209b51cc674b851aa0b0.png)\n\n# 事务隔离级别越高，安全性越高，性能就越低下。\n# Oracle默认的事务隔离级别为：READ-COMMITTED\n# Mysql默认的事务隔离级别为：REPEATABLE-READ\n\n\n', '#10005#', 0, 1, b'0', '2019-11-16 22:15:35', 1, '2019-11-16 22:16:10', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10027, '关键语法', '# 关键语法\n- GROUP BY\n	- 满足\"SELECT子句中的列名必须为分组列或列函数\"\n	- 列函数对于group by子句定义的每个组各返回一个结果\n- HAVING\n	- 通常与GROUP BY子句一起使用\n	- Where过滤行，HAVING过滤组\n	- 出现在同一sql的顺序:WHERE > GROUP BY > HAVING\n\n查询没有学全所有课的同学的学号、姓名\n```sql\nselect stu.student_id,stu.name\nfrom student stu, score s\nwhere stu.student_id = s.student_id\ngroup by s.student_id\nhaving count(*) < (select count(*) from course)\n```\n\n\n', '#10005#', 0, 1, b'0', '2019-11-16 23:10:54', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10028, '缓存知识---Redis', '# 主流应用架构\n![image.png](/fileServer/2019-11-17/2cd07581455fdcb792089baeec9ba34a.png)\n\n# 缓存中间件---Memcache和Redis的区别\n## Memcache:代码层次类似Hash\n- 支持简单数据类型\n- 不支持数据持久化存储\n- 不支持主从\n- 不支持分片\n\n## Redis\n- 数据类型丰富\n- 支持数据磁盘持久化存储\n- 支持主从\n- 支持分片\n\n\n# 为什么Redis能这么快\n## 100000+QPS （QPS即query per second，每秒内查询次数）\n- 完全基于内存，绝大部分请求时纯粹的内存操作，执行效率高\n- 数据结构简单，对数据操作也简单\n- 采用单线程，单线程也能处理高并发请求，想多核也可以启动多实例\n- 使用多路I/O复用模型，非阻塞IO\n\n# 多路I/O复用模型\n## FD：File Descriptor，文件描述符\n- 一个开打的文件通过唯一的描述符进行引用，该描述符时打开文件的元数据到文件本身的映射\n\n## Select系统调用\n![image.png](/fileServer/2019-11-17/73d2ccde6f9a4862dec50eb62d1e8657.png)\n\n## Redis采用的I/O多路复用函数：epoll/kqueue/evport/select？\n- 因地制宜（根据系统选择）\n- 优先选择时间复杂度O(1)的I/O多路复用函数作为底层实现\n- 以时间复杂度为O(n)的select作为保底\n- 基于react设计模式监听I/O事件\n\n## 传统的阻塞I/O模型\n![image.png](/fileServer/2019-11-17/7acb056a5f48c1c04c9d741c34362c15.png)\n\n', '#10006#', 0, 3, b'0', '2019-11-17 09:43:53', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10029, 'Redis常用的数据类型', '# 供用户使用的数据类型\n- String：最基本的数据类型，二进制安全（get，set）\n- Hash：String元素组成的字典，适合用于存储对象（hmset，hget）\n- List:列表，按照String元素插入顺序排序（lpush，lrange）\n- Set：String元素组成的无序集合，通过哈希表实现，不允许重复（sadd，smembers）提供了求交集，并集，差集等操作（可适用于微博的共同关注，共同喜好）\n- Sorted Set:通过分数来为集合中的成员进行从小到大的排序（zadd，zrangebyscore）提供了权重排序\n- 用于计数的HyperLogLog，用于支持存储地理位置信息的Geo\n\n# 底层数据类型基础（有时间可以去了解一下）\n1. 简单动态字符串\n2. 链表\n3. 字典\n4. 跳跃表\n5. 整树集合\n6. 压缩列表\n7. 对象\n\n', '#10006#', 0, 2, b'0', '2019-11-17 09:56:32', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10030, '从海量Key里查询出某一固定前缀的Key', '# 留意细节\n- 摸清数据规模，问清楚边界\n\n# 使用keys对线上的业务的影响\n## keys pattern：查找所有符合给定模式pattern的key\n- keys 指令一次性返回所有匹配的key\n- 键的数量过大会使服务卡顿\n\n## scan cursor [MATCH pattern][COUNT count]\n- 基于游标的迭代器，需要基于上一次的游标延续之前的迭代过程\n- 以0作为游标开始一次新的迭代，知道命令返回游标0完成一次遍历\n- 不保证每次执行都返回某个给定数量的元素，支持模糊查询\n- 一次返回的数量不可控，只能是==大概率==符合count参数\n\n', '#10006#', 0, 4, b'0', '2019-11-17 10:05:43', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10031, '如何通过Redis实现分布式锁', '# 分布式锁需要解决的问题？\n- 互斥性\n- 安全性\n- 死锁\n- 容错\n\n## SETNX key value:如果key不存在，则创建并赋值\n- 时间复杂度：O(1)\n- 返回值：设置成功，返回1；设置失败，返回0。\n\n# 如何解决SETNX长期有效的问题？\n- EXPIRE key seconds\n- 设置key的生存时间，当key过期时（生存时间为0），会被自动删除\n- 缺点：原子性得不到满足\n\n伪代码示例如下：\n```java\n// 设置key\nlong status = RedisUtils.setnx(key, \"1\");\n// 设置key成功\nif (status == 1) {\n    RedisUtils.expire(key, expire);\n    // 执行独占资源逻辑\n    doOcuppiedWork();		\n}\n```\n==注：上述程序有一个问题就是，如果执行了设置key之后，程序挂掉了，那就出现了死锁的状况。==\n\n## 正确姿势\n## SET key value [EX seconds] [PX milliseconds] [NX|XX]\n- EX second:设置键的过期时间为second秒\n- PX millisecond：设置键的过期时间为millisecond毫秒\n- NX：只要键不存在时，才对键进行设置操作\n- XX：只要键已存在时，才对键进行设置操作\n- SET操作成功时，返回OK，否则返回nil\n```java\n// 设置key\nString result = RedisUtils.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);\n// 设置key成功\nif (\"OK\".equals(result)) {\n    // 执行独占资源逻辑\n    doOcuppiedWork();		\n}\n```\n\n# 大量的key同时过期的注意事项\n## 集中过期，由于清楚大量的key很耗时，会出现短暂的卡顿现象\n- 解决方案:在设置key的过期时间的时候，给每个key加上一个随机值\n\n\n\n\n\n', '#10006#', 0, 1, b'0', '2019-11-17 10:28:12', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10032, '如何使用Redis做异步队列', '# 使用List作为队列，RPUSH生产消息，LPOP消费消息\n- 缺点：没有等待队列里有值就直接消费\n- 弥补：可以通过在应用层引入Sleep机制去调用LPOP重试\n- 或则选用BLPOP key [key ...] timeout:阻塞知道队列有消息或者超时\n- 缺点：只能供一个消费者消费\n\n# 使用pub/sub主题订阅者模式\n- 发送者（pub）发送消息，订阅者（sub）接收消息\n- 订阅者可以订阅任意数量的频道\n- 缺点：消息的发布时无状态的，无法保证可达\n![image.png](/fileServer/2019-11-17/ffe085fb2ce408737e93090277344926.png)\n', '#10006#', 0, 2, b'0', '2019-11-17 10:45:05', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10033, 'Redis如何做持久化', '# RDB(快照) 持久化：保存某个时间点的全量数据快照\n- 在redis.conf文件中\n![image.png](/fileServer/2019-11-17/861aef520e1ad277b5035740f13ccbae.png)\n- 通过配置多个持久化策略来实现RDB\n- （配置项）stop-writes-on-bgsave-error yes（备份出错时，主进程停止接受写入操作）\n- （配置项）rdbcompression yes（是否开启rdb文件压缩，不建议开启）\n- SAVE：阻塞Redis的服务器进程，知道RDB文件被创建完毕\n- BGSAVE：Fork出一个子进程来创建RDB文件，不阻塞服务器进程\n- lastsave：上一次执行save的时间\n\n# 自动化触发RDB持久化的方式\n- 根据redis.conf配置里的SAVE m n 定时触发（用的时BGSAVE）\n- 主从复制时，主节点自动触发\n- 执行Debug Reload\n- 执行Shutdown且没有开启AOF持久化\n\n# BGSAVE原理\n![image.png](/fileServer/2019-11-17/341975df5e7db1a2c3cee7bb8837f52.png)\n## 系统调用fork():创建进程，实现了Copy-on-Write\n- ==Copy-on-Write：如果有多个调用者同时要求相同的资源（如内存或磁盘上的数据存储），他们会共同获取相同的指针指向相同的资源，直到某个调用者试图修改资源的内容时，系统才会真正的复制一份专用的副本给该调用者，而其他调用者所见到的最初的资源仍然保持不变==\n\n# RDB持久化缺点\n- 内存数据的全量同步，数据量大会由于I/O而严重影响性能\n- 可能会因为Redis挂掉而丢失从当前至最后一次快照期间的数据\n\n# AOF（Append-Only-File）持久化：保存写状态\n- 记录下除了查询以外的所有变更数据库状态的指令\n- 以append的形式追加保存到AOF文件中（增量）\n- 默认时关闭的，可以通过修改配置文件来开启\n- （配置项）appendonly yes \n- （配置项）appendfilename \'appendonly.aof\'\n- （配置项）appendsync always/everysec/no\n\n## 日志重写解决AOF文件大小不断增加的问题，原理如下：\n- 调用fork（），创建一个子进程\n- 子进程把新的AOF写到一个临时文件里，不依赖原来的AOF文件\n- 主进程持续将新的变动同时写到内存和原来的AOF里\n- 主进程获取子进程重写AOF的完成信号，往新的AOF同步增量变动\n- 使用新的AOF文件替换掉旧的AOF文件\n\n# Redis数据的恢复\n## RDB和AOF文件共享情况的恢复流程\n![image.png](/fileServer/2019-11-17/82df1bf8416823cbb2f937fb2f37f141.png) \n\n\n# RDB和AOF的优缺点\n- RDB优点：全量数据的快照，文件小，恢复块\n- RBD缺点：无法保存最近一次快照之后的数据\n- AOF的优点：可读性高，适合保存增量数据，数据不易丢失\n- AOF的缺点：文件体积大，恢复时间长\n\n# RBD-AOF混合持久化方式\n- BGSAVE做镜像全量持久化，AOF做增量持久化\n', '#10006#', 0, 8, b'0', '2019-11-17 11:15:02', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10034, '使用Pipeline的好处', '# 使用Pipeline的好处\n- Pipeline和Linux的管道类似\n- Redis基于请求/响应模型，单个请求处理需要一一应答\n- Pipeline批量执行指令，节省多次IO往返的时间\n- 有顺序依赖的指令建议分批发送\n\n# Redis的同步机制\n- 主从同步原理\n- ![image.png](/fileServer/2019-11-17/4295eb8b2df6f7b4e65d6f32d0df7550.png)\n\n## 全同步过程\n- Salve发送sync命令到Master\n- Master启动一个后台进程，将Redis中数据快照保存到文件中\n- Master将保存数据快照期间接收到的写命令缓存起来\n- Master完成写文件操作后，将该文件发送给Slave\n- 使用新的AOF文件替换掉旧的AOF文件\n- Master将这期间手机的增量写命令发送给Salve端\n\n## 增量同步过程\n- Master接收到用户的操作指令，判断是否需要传播到Slave\n- 将操作记录追加到AOF文件\n- 将操作传播到其他Slave：1、对齐主从库；2、往响应缓存写入指令\n- 将缓存中的数据发送给Slave\n\n# Reids Sentinel（哨兵模式）\n## 解决主从同步Master宕机后的主从切换问题：\n- 监控：检查主从服务器是否运行正常\n- 提醒：通过API向管理员或者其他应用程序发送故障通知\n- 自动故障迁移：主从切换\n\n# 流言协议Gossip\n## 在杂乱无章中寻求一致\n- 每个节点都随机地与对方通信，最终所有节点的状态达成一致\n- 种子节点定期随机向其他节点发送节点列表以及需要传播的消息\n- 不保证信息一定会传递给所有节点，但是最终会趋于一致\n\n\n', '#10006#', 0, 6, b'0', '2019-11-17 11:26:35', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10035, 'Redis的集群原理', '# 如何从海量数据中快速找到所需？\n- 分片：按照某种规则去划分数据，分散存储在多个节点上\n- 常规的按照哈希划分无法实现节点的动态增减\n\n# 一致性哈希算法：对2^32取模，将哈希值空间组织成虚拟的圆环\n![image.png](/fileServer/2019-11-17/bd02c3272f978b32e3d6fb36df275e9.png)\n\n# Node C 宕机\n![image.png](/fileServer/2019-11-17/216fdd20d9d363f7478b698b566c7648.png)\n\n# 新增服务器Node X\n![image.png](/fileServer/2019-11-17/5af58daf2839d0d6e3fdd3c4df5de2ab.png)\n\n# Hash环的数据倾斜问题\n![image.png](/fileServer/2019-11-17/7e072ebe6446b3d189e535ac0665782b.png)\n\n# 引入虚拟节点解决数据倾斜问题\n![image.png](/fileServer/2019-11-17/34cfd38d71fa068bc715ba916547e81a.png)', '#10006#', 0, 4, b'0', '2019-11-17 11:34:01', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10036, '算法复杂度', '在描述算法复杂度时,经常用到o(1), o(n), o(logn), o(nlogn)来表示对应算法的时间复杂度, 这里进行归纳一下它们代表的含义: \n这是算法的时空复杂度的表示。不仅仅用于表示==时间复杂度==，也用于表示==空间复杂度==。 \n\nO后面的括号中有一个函数，指明某个算法的耗时/耗空间与数据增长量之间的关系。其中的n代表输入数据的量。 \n\n比如时间复杂度为O(n)，就代表数据量增大几倍，耗时也增大几倍。比如常见的遍历算法。\n \n再比如时间复杂度O(n^2)，就代表数据量增大n倍时，耗时增大n的平方倍，这是比线性更高的时间复杂度。比如冒泡排序，就是典型的O(n^2)的算法，对n个数排序，需要扫描n×n次。\n\n再比如O(logn)，当数据增大n倍时，耗时增大logn倍（这里的log是以2为底的，比如，当数据增大256倍时，耗时只增大8倍，是比线性还要低的时间复杂度）。\n二分查找就是O(logn)的算法，每找一次排除一半的可能，256个数据中查找只要找8次就可以找到目标。\n==注：如果a的x次方等于N（a>0，且a不等于1），那么数x叫做以a为底N的对数（logarithm），记作x=logaN。其中，a叫做对数的底数，N叫做真数。==\n\nO(nlogn)同理，就是n乘以logn，当数据增大256倍时，耗时增大256*8=2048倍。这个复杂度高于线性低于平方。归并排序就是O(nlogn)的时间复杂度。 \n\nO(1)就是最低的时空复杂度了，也就是耗时/耗空间与输入数据大小无关，无论输入数据增大多少倍，耗时/耗空间都不变。 哈希算法就是典型的O(1)时间复杂度，无论数据规模多大，都可以在一次计算后找到目标（不考虑冲突的话）', '#10007#', 0, 4, b'0', '2019-11-17 11:38:52', 1, '2019-11-17 11:40:29', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10037, 'Linux体系结构', '# Linux\n![image.png](/fileServer/2019-11-17/b1326a334e2e4788c9da891e0490fa25.png)\n## 体系结构主要分为用户态（用户上层活动）和内核态\n## 内核：本质是一段管理计算机硬件设备的程序\n## 系统调用：内核的访问接口，是一种能再简化的操作\n## 公用函数库：系统调用的组合拳\n## ==Shell：命令解释器，可编程==\n\n# 如何查找特定的文件\n## find\n- 语法：find path [options] params\n- 作用：在指定目录下查找文件\n- 例如查找当前目录：find -name \"target3.java\"\n- 例如全局搜索: find / -name \"target3.java\"\n- 在当前home目录下模糊查找：find ~ -name \"target*\"\n- 在当前home目录下模糊查找：find ~ -iname \"target*\" (-i忽略大小写)\n\n# 检索文件内容\n## grep\n- 语法：grep [options] pattern file\n- 全程：Global Regular Expression Print\n- 作用：查找文件里符合条件的字符串\n- grep \"moo\" target*\n- grep \"haha\" target*\n\n## 管道操作符 |\n- 可将指令连接起来，前一个指令的输出作为后一个指令的输入\n- 只处理前一个命令正确输出，不处理错误输出\n- 右边命令必须能够接收标准输入流，否则传递过程中数据会被抛弃\n- 获取文件中的partial[true]:grep \'partial\\[true\\]\' 文件名\n- 获取文件中的partial[true]后面的engine:grep \'partial\\[true\\]\' 文件名 | grep -o \'engine\\[[0-9a-z]*\\]\'\n- 查找tomcat：ps -ef | grep tomcat\n- 过滤本身：ps -ef | grep tomcat | grep -v \'grep\' \n\n## 对文件内容做统计\n- 语法：awk [options] \'cmd\' file\n- 一次读取一行文本，按输入分隔符进行切片，切成多个组成部分\n- 将切片直接保存在内建的变量中，$1,$2...（$0表示行的全部）\n- 支持对单个切片的判断，支持循环判断，默认的分隔符位空格\n- 例如：awk \'{print $1,$4}\' 文件名\n- 例如：awk \'$1 == \"tcp\" && $2 == 1 {print $0}\' 文件名\n- 例如：awk \'($1 == \"tcp\" && $2 == 1) || NR == 1 {print $0}\' 文件名\n- 例如：awk -F \",\" \'{print $2}\' 文件名\n\n## 批量替换文本内容\n- 语法：sed [option] \'sed command\' filename\n- 全名stream editor，流编辑器\n- 适合用于对文本的行内容进行处理\n- sed \'s/^Str/String/\' replace.java(这种只是将结果输出到控制台)\n- sed -i \'s/^Str/String/\' replace.java(这种会修改文本)\n- sed -i \'s/\\.$/\\;/\' replace.java(将末尾的.修改位;)\n- sed -i \'s/Jack/me/\' replace.java(替换每行首次匹配的Jack为me)\n- sed -i \'s/Jack/me/g\' replace.java(替换每行匹配的Jack为me)\n- sed -i \'/^ *$/d\' replace.java(替换空行)\n\n', '#10008#', 0, 3, b'0', '2019-11-17 11:51:58', 1, '2019-11-17 15:37:39', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10038, 'Compile Once Run Anywhere', '# javap -help 帮助\n# javap -c 对代码进行反汇编\n\n![image.png](/fileServer/2019-11-17/f95850ba700c52ca5ebfade6633fc77b.png)\n- Java源码首先被编译成字节码，再由不同平台的JVM进行解析，Java语言在不同的平台上运行时不需要进行重新编译，Java虚拟机在执行字节码的时候，把字节码转换成具体平台上的机器指令。\n\n# 为什么JVM不直接将源码解析成机器码去执行\n- 准备工作：每次执行都需要各种检查\n- 兼容性：也可以将别的语言解释成字节码\n\n# Java如何加载并执行class文件\n![image.png](/fileServer/2019-11-17/ece03b45ba64b46d0e9f86f503125dfb.png)\n\n\n\n', '#10009#', 0, 1, b'0', '2019-11-17 16:11:33', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10039, '反射', '### JAVA 反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；\n### 对于任意一个对象，都能够调用它的任意方法和属性；\n### 这种动态获取信息以及动态调用对象方法的功能成为java语言的反射机制。\n\n写一个反射的例子\n```java\npublic class Robot {\n\n    private String name;\n\n    public void sayHi(String helloSentence) {\n        System.out.println(helloSentence + \" \" + name);\n    }\n\n    private String throwHello(String tag) {\n        return \"Hello \" + tag;\n    }\n\n}\n```\n\n```java\npublic class ReflectSample {\n\n    public static void main(String[] args) throws ClassNotFoundException,\n            IllegalAccessException, InstantiationException,\n            NoSuchMethodException, InvocationTargetException, NoSuchFieldException {\n\n        Class<?> clazz = Class.forName(\"reflect.Robot\");\n        Robot robot = (Robot) clazz.newInstance();\n        System.out.println(\"Class name is \" + clazz.getName());\n        /**\n         * getDeclaredMethod：获取当前类的所有声明的方法，\n         * 包括public、protected和private修饰的方法。\n         * 需要注意的是，这些方法一定是在当前类中声明的，\n         * 从父类中继承的不算，实现接口的方法由于有声明所以包括在内。\n         */\n        Method throwHello = clazz.getDeclaredMethod(\"throwHello\", String.class);\n        // 不设置Accessible会报错\n        throwHello.setAccessible(true);\n        Object throwHelloResult = throwHello.invoke(robot, \"Bob\");\n        System.out.println(\"throwHello result is \" + throwHelloResult);\n\n        /**\n         * getMethod：获取当前类和父类的所有public的方法。\n         * 这里的父类，指的是继承层次中的所有父类。\n         * 比如说，A继承B，B继承C，那么B和C都属于A的父类\n         */\n        Method sayHi = clazz.getMethod(\"sayHi\", String.class);\n        sayHi.invoke(robot, \"sayHi\");\n\n        Field name = clazz.getDeclaredField(\"name\");\n        name.setAccessible(true);\n        name.set(robot, \"K\");\n        sayHi.invoke(robot, \"sayHi\");\n    }\n\n}\n```\n', '#10009#', 0, 4, b'0', '2019-11-17 16:15:36', 1, '2019-11-17 16:32:10', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10040, '谈谈ClassLoader', '# 类从编译到执行的过程\n- 编译器将Robot.java源文件编译为Robot.class字节码文件\n- ClassLoader将字节码转换为JVM中的Class<Robot>对象\n- JVM利用Class<Robot>对象实例化Robot对象\n\n# 谈谈ClassLoader\n### ClassLoader在Java中有着非常重要的作用，它主要工作在Class装载的加载阶段，其主要作用是从系统外部获得Class二进制数据流，它是Java的核心组件，所有的Class都是由ClassLoader进行加载的，ClassLoader负责通过将Class文件中的二进制数据流装载进系统，然后交给Java虚拟机进行连接，初始化等操作\n\n# ClassLoader的种类\n- BootStrapClassLoader：C++编写，加载核心库java.*\n- ExtClassLoaderL:Java编写，加载扩展库javax.*\n- AppClassLoader:Java编写，加载程序所在目录\n- 自定义ClassLoader：Java编写，定制化加载\n\n# 自定义ClassLoader的实现\n## 关键函数\n![image.png](/fileServer/2019-11-17/ba07bb140e1c8cc70d08aee4efaa792c.png)\n\n## 代码示例如下\n```java\npublic class MyClassLoader extends ClassLoader {\n\n    private String path;\n    private String classLoaderName;\n\n    public MyClassLoader(String path, String classLoaderName) {\n        this.path = path;\n        this.classLoaderName = classLoaderName;\n    }\n\n    /**\n     * 用于寻找类文件\n     */\n    @Override\n    protected Class<?> findClass(String name) throws ClassNotFoundException {\n        byte[] bytes = loadClassData(name);\n        return defineClass(name, bytes, 0, bytes.length);\n    }\n\n    /**\n     * 用于加载类文件\n     */\n    private byte[] loadClassData(String name) {\n        name = path + name + \".class\";\n        BufferedInputStream bis = null;\n        ByteArrayOutputStream bos = null;\n        try {\n            bis = new BufferedInputStream(new FileInputStream(new File(name)));\n            bos = new ByteArrayOutputStream();\n            int len = 0;\n            byte[] buffer = new byte[1024];\n            while (-1 != (len = bis.read(buffer))) {\n                bos.write(buffer, 0, len);\n            }\n        } catch (Exception e) {\n            e.printStackTrace();\n        } finally {\n            try {\n                if (bis != null) {\n                    bis.close();\n                }\n                if (bos != null) {\n                    bos.close();\n                }\n            } catch (Exception e) {\n                e.printStackTrace();\n            }\n        }\n        return bos.toByteArray();\n    }\n\n}\n```\n\n```java\npublic class ClassLoaderChecker {\n\n    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {\n        MyClassLoader mc = new MyClassLoader(\"C:\\\\Users\\\\82186\\\\Desktop\\\\\", \"myClassLoader\");\n        Class<?> helloWorld = mc.findClass(\"HelloWorld\");\n        System.out.println(helloWorld.getClassLoader());\n        helloWorld.newInstance();\n    }\n\n}\n```\n\n- 不仅可以在自定义目录加载class文件，也可以通过远程网络获取二进制流\n- 也可以对class文件进行加密，在findClass中进行解密\n- ==修改字节码流（字节码增强）---ASM==\n\n\n', '#10009#', 0, 4, b'0', '2019-11-17 17:20:39', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10041, '谈谈类加载器的双亲委派机制', '![image.png](/fileServer/2019-11-17/9b58a873b4646d2238949593c5bda326.png)\n\n# ==这里的代码详见ClassLoader中的loadClass方法，很经典==\n\n- 避免多份同样字节码的加载', '#10009#', 0, 6, b'0', '2019-11-17 17:34:50', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_article` VALUES (10042, '类的加载方式', '# 类的加载方式\n### 隐式加载：new\n### 显式加载：loadClass，forName等\n\n# loadClass和forName的区别\n## 类的装载过程\n![image.png](/fileServer/2019-11-17/bc3e0438940905cf293c108815c77976.png)\n## 结论\n- Class.forName得到的class是已经初始化完成的\n- ClassLoader.loadClass得到的class是还没有链接的（加快初始化速度）\n\n\n', '#10009#', 0, 10, b'0', '2019-11-17 17:42:20', 1, '2019-11-17 17:44:36', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10043, '博客进展', '# 今天把留言板功能实现了一下\n# 接下来可以把后台的留言管理也实现下\n# 还有热门文章和文章归档可以一起先实现一下\n# 自己也测试下有没有问题。。。\n\n# 该文章提到的内容均已实现---更新于：2019-11-19 13:36:36', '#2#', 0, 8, b'0', '2019-11-18 12:26:06', 1, '2019-11-19 13:36:43', 1, b'0', NULL);
INSERT INTO `blog_article` VALUES (10044, '个人记录', '# 后台管理页面目前不支持手机浏览器自适应，后面看看能不能处理', '#10004#', 0, 0, b'1', '2019-11-18 23:03:56', 1, NULL, NULL, b'0', NULL);

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` bigint(16) NULL DEFAULT NULL COMMENT '文章id',
  `tag_id` bigint(16) NULL DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10096 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客文章标签关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article_tag
-- ----------------------------
INSERT INTO `blog_article_tag` VALUES (10007, 10000, 10000);
INSERT INTO `blog_article_tag` VALUES (10015, 10001, 10001);
INSERT INTO `blog_article_tag` VALUES (10016, 10001, 10002);
INSERT INTO `blog_article_tag` VALUES (10024, 1, 2);
INSERT INTO `blog_article_tag` VALUES (10032, 10009, 10003);
INSERT INTO `blog_article_tag` VALUES (10037, 10010, 10003);
INSERT INTO `blog_article_tag` VALUES (10039, 10011, 10003);
INSERT INTO `blog_article_tag` VALUES (10040, 10012, 10003);
INSERT INTO `blog_article_tag` VALUES (10041, 10013, 10003);
INSERT INTO `blog_article_tag` VALUES (10042, 10014, 2);
INSERT INTO `blog_article_tag` VALUES (10044, 10015, 10003);
INSERT INTO `blog_article_tag` VALUES (10046, 10016, 10003);
INSERT INTO `blog_article_tag` VALUES (10048, 10017, 10003);
INSERT INTO `blog_article_tag` VALUES (10054, 10018, 10005);
INSERT INTO `blog_article_tag` VALUES (10056, 10019, 10005);
INSERT INTO `blog_article_tag` VALUES (10057, 10020, 10005);
INSERT INTO `blog_article_tag` VALUES (10059, 10021, 10005);
INSERT INTO `blog_article_tag` VALUES (10061, 10022, 10005);
INSERT INTO `blog_article_tag` VALUES (10063, 10024, 10005);
INSERT INTO `blog_article_tag` VALUES (10065, 10025, 10005);
INSERT INTO `blog_article_tag` VALUES (10067, 10027, 10005);
INSERT INTO `blog_article_tag` VALUES (10068, 10028, 10006);
INSERT INTO `blog_article_tag` VALUES (10069, 10029, 10006);
INSERT INTO `blog_article_tag` VALUES (10070, 10030, 10006);
INSERT INTO `blog_article_tag` VALUES (10071, 10031, 10006);
INSERT INTO `blog_article_tag` VALUES (10072, 10032, 10006);
INSERT INTO `blog_article_tag` VALUES (10073, 10033, 10006);
INSERT INTO `blog_article_tag` VALUES (10074, 10034, 10006);
INSERT INTO `blog_article_tag` VALUES (10075, 10035, 10006);
INSERT INTO `blog_article_tag` VALUES (10078, 10036, 10007);
INSERT INTO `blog_article_tag` VALUES (10081, 10037, 10008);
INSERT INTO `blog_article_tag` VALUES (10082, 10038, 10009);
INSERT INTO `blog_article_tag` VALUES (10085, 10039, 10009);
INSERT INTO `blog_article_tag` VALUES (10086, 10040, 10009);
INSERT INTO `blog_article_tag` VALUES (10087, 10041, 10009);
INSERT INTO `blog_article_tag` VALUES (10089, 10042, 10009);
INSERT INTO `blog_article_tag` VALUES (10093, 10044, 10004);
INSERT INTO `blog_article_tag` VALUES (10095, 10043, 2);

-- ----------------------------
-- Table structure for blog_msg_board
-- ----------------------------
DROP TABLE IF EXISTS `blog_msg_board`;
CREATE TABLE `blog_msg_board`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(16) NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '留言ip',
  `content` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '留言内容',
  `color` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点颜色',
  `icon` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点图标',
  `parent_id` bigint(16) NULL DEFAULT NULL COMMENT '父级id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(16) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(16) NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '数据有效位（0：有效   1：删除）',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客留言板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_msg_board
-- ----------------------------
INSERT INTO `blog_msg_board` VALUES (1, 1, NULL, '1L我的。。。', '#316A4C', NULL, 0, '2019-11-19 13:16:36', 1, NULL, NULL, b'0', '1');
INSERT INTO `blog_msg_board` VALUES (2, 1, NULL, '欢迎大家留言~', '#32560E', NULL, 0, '2019-11-19 13:16:52', 1, NULL, NULL, b'0', '2');

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
) ENGINE = InnoDB AUTO_INCREMENT = 10010 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES (1, '测试', 1, '2019-11-08 16:19:41', 1, '2019-11-08 16:19:47', 1, b'0', NULL);
INSERT INTO `blog_tag` VALUES (2, '个人记事', 2, '2019-11-10 21:51:50', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_tag` VALUES (10000, 'Markdown', 4, '2019-11-13 08:44:44', 1, '2019-11-16 09:35:52', 1, b'0', NULL);
INSERT INTO `blog_tag` VALUES (10001, 'Shiro', 30, '2019-11-15 13:40:58', 1, '2019-11-17 15:40:46', 1, b'0', NULL);
INSERT INTO `blog_tag` VALUES (10002, 'JWT', 31, '2019-11-15 13:43:52', 1, '2019-11-17 15:41:15', 1, b'0', NULL);
INSERT INTO `blog_tag` VALUES (10003, '网络基础', 13, '2019-11-15 22:15:36', 1, '2019-11-17 11:34:52', 1, b'0', NULL);
INSERT INTO `blog_tag` VALUES (10004, '个人BUG', 3, '2019-11-16 09:36:11', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_tag` VALUES (10005, '数据库', 15, '2019-11-16 12:51:42', 1, '2019-11-17 11:34:58', 1, b'0', NULL);
INSERT INTO `blog_tag` VALUES (10006, 'Redis', 17, '2019-11-17 09:29:58', 1, '2019-11-17 15:41:08', 1, b'0', NULL);
INSERT INTO `blog_tag` VALUES (10007, '计算机基础', 11, '2019-11-17 11:34:18', 1, '2019-11-17 11:34:44', 1, b'0', NULL);
INSERT INTO `blog_tag` VALUES (10008, 'Linux', 10, '2019-11-17 11:36:11', 1, NULL, NULL, b'0', NULL);
INSERT INTO `blog_tag` VALUES (10009, 'Java', 20, '2019-11-17 15:41:37', 1, NULL, NULL, b'0', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 10006 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '公共权限', '', '', '', '', 0, '', 0, '0', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (10, '刷新菜单权限', '/system/menu/refreshPermission', 'GET', '', '', 1, '', 1, '2', b'0', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (11, '获取菜单树', '/system/menu/getMenuTree', 'GET', '', '', 1, '', 2, '2', b'0', '2019-11-07 10:10:10', 0, '2019-11-15 17:33:22', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (12, '上传文件', '/file/upload', 'POST', '', '', 1, '', 3, '2', b'0', '2019-11-07 10:10:10', 0, '2019-11-15 17:33:24', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (13, '获取全部标签', '/blog/tag/getAllTag', 'GET', '', '', 2100, '', 5, '2', b'0', '2019-11-08 17:30:10', 1, '2019-11-15 17:33:17', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (14, '修改密码', '/system/user/changePassword', 'POST', '', '', 1, '', 4, '2', b'0', '2019-11-18 16:29:32', 1, NULL, NULL, b'0', NULL);
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
INSERT INTO `sys_menu` VALUES (2200, '标签管理', '', '', 'tag', 'BlogTag', 2000, 'el-icon-collection-tag', 2, '1', b'0', '2019-11-08 16:12:17', 1, '2019-11-08 16:12:26', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2201, '标签列表', '/blog/tag', 'GET', '', '', 2200, '', 1, '2', b'0', '2019-11-08 16:15:58', 1, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2202, '新增标签', '/blog/tag', 'POST', '', '', 2200, '', 2, '2', b'0', '2019-11-08 16:16:23', 1, '2019-11-08 16:16:30', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2203, '修改标签', '/blog/tag', 'PUT', '', '', 2200, '', 3, '2', b'0', '2019-11-08 16:16:57', 1, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2204, '删除标签', '/blog/tag', 'DELETE', '', '', 2200, '', 4, '2', b'0', '2019-11-08 16:17:19', 1, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2300, '留言管理', '', '', 'message', 'BlogMessage', 2000, 'el-icon-s-comment', 3, '1', b'0', '2019-11-19 09:04:51', 1, '2019-11-19 09:06:03', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2301, '留言列表', '/blog/message', 'GET', '', '', 2300, '', 1, '2', b'0', '2019-11-19 11:42:56', 1, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2302, '新增留言', '/blog/message', 'POST', '', '', 2300, '', 2, '2', b'0', '2019-11-19 11:43:17', 1, '2019-11-19 11:43:30', 1, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2303, '修改留言', '/blog/message', 'PUT', '', '', 2300, '', 3, '2', b'0', '2019-11-19 11:43:48', 1, NULL, NULL, b'0', NULL);
INSERT INTO `sys_menu` VALUES (2304, '删除留言', '/blog/message', 'DELETE', '', '', 2300, '', 4, '2', b'0', '2019-11-19 11:44:07', 1, NULL, NULL, b'0', NULL);

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
INSERT INTO `sys_role` VALUES (2, 'ROLE_TEST', '测试', '测试角色', '2019-11-07 10:10:10', 0, '2019-11-19 13:50:15', 1, b'0', NULL);
INSERT INTO `sys_role` VALUES (3, 'ROLE_GUEST', '游客', '游客角色', '2019-11-07 10:10:10', 0, '2019-11-19 13:50:29', 1, b'0', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(16) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(16) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10195 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (10154, 2, 2304);
INSERT INTO `sys_role_menu` VALUES (10155, 2, 1);
INSERT INTO `sys_role_menu` VALUES (10156, 2, 1000);
INSERT INTO `sys_role_menu` VALUES (10157, 2, 11);
INSERT INTO `sys_role_menu` VALUES (10158, 2, 1100);
INSERT INTO `sys_role_menu` VALUES (10159, 2, 1101);
INSERT INTO `sys_role_menu` VALUES (10160, 2, 13);
INSERT INTO `sys_role_menu` VALUES (10161, 2, 1200);
INSERT INTO `sys_role_menu` VALUES (10162, 2, 2000);
INSERT INTO `sys_role_menu` VALUES (10163, 2, 1105);
INSERT INTO `sys_role_menu` VALUES (10164, 2, 1201);
INSERT INTO `sys_role_menu` VALUES (10165, 2, 1300);
INSERT INTO `sys_role_menu` VALUES (10166, 2, 2100);
INSERT INTO `sys_role_menu` VALUES (10167, 2, 1301);
INSERT INTO `sys_role_menu` VALUES (10168, 2, 2101);
INSERT INTO `sys_role_menu` VALUES (10169, 2, 1206);
INSERT INTO `sys_role_menu` VALUES (10170, 2, 2200);
INSERT INTO `sys_role_menu` VALUES (10171, 2, 1305);
INSERT INTO `sys_role_menu` VALUES (10172, 2, 2201);
INSERT INTO `sys_role_menu` VALUES (10173, 2, 2300);
INSERT INTO `sys_role_menu` VALUES (10174, 2, 2301);
INSERT INTO `sys_role_menu` VALUES (10175, 3, 1);
INSERT INTO `sys_role_menu` VALUES (10176, 3, 1000);
INSERT INTO `sys_role_menu` VALUES (10177, 3, 11);
INSERT INTO `sys_role_menu` VALUES (10178, 3, 1100);
INSERT INTO `sys_role_menu` VALUES (10179, 3, 1101);
INSERT INTO `sys_role_menu` VALUES (10180, 3, 13);
INSERT INTO `sys_role_menu` VALUES (10181, 3, 1200);
INSERT INTO `sys_role_menu` VALUES (10182, 3, 2000);
INSERT INTO `sys_role_menu` VALUES (10183, 3, 1105);
INSERT INTO `sys_role_menu` VALUES (10184, 3, 1201);
INSERT INTO `sys_role_menu` VALUES (10185, 3, 1300);
INSERT INTO `sys_role_menu` VALUES (10186, 3, 2100);
INSERT INTO `sys_role_menu` VALUES (10187, 3, 1301);
INSERT INTO `sys_role_menu` VALUES (10188, 3, 2101);
INSERT INTO `sys_role_menu` VALUES (10189, 3, 1206);
INSERT INTO `sys_role_menu` VALUES (10190, 3, 2200);
INSERT INTO `sys_role_menu` VALUES (10191, 3, 1305);
INSERT INTO `sys_role_menu` VALUES (10192, 3, 2201);
INSERT INTO `sys_role_menu` VALUES (10193, 3, 2300);
INSERT INTO `sys_role_menu` VALUES (10194, 3, 2301);

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
INSERT INTO `sys_user` VALUES (1, NULL, 'fighting', '$2a$10$iO2d1tbF35GZ4lc0Xen6KOV0SllbnRiyrg3Fl.fOX27gppE3csnay', '奋斗把，少年!', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', '', '821863269@qq.com', b'1', '2019-11-07 10:10:10', 0, '2019-11-18 22:54:36', 1, b'0', NULL);
INSERT INTO `sys_user` VALUES (2, NULL, 'test', '$2a$10$/5GakDw0yneu1TVW2/fkSOudI7oPpXidAtiVudRCICfR1vQ/3D5tu', '测试', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', '', '821863269@qq.com', b'1', '2019-11-07 10:10:10', 0, '2019-11-19 11:40:47', 2, b'0', NULL);
INSERT INTO `sys_user` VALUES (3, NULL, 'guest', '$2a$10$j9iOKR6IL4WtZZ0xFNse1eaeXc9DRz9zpZOzCjIZNXMceqZBOqV0u', '游客', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', '', '821863269@qq.com', b'1', '2019-11-07 10:10:10', 0, NULL, NULL, b'0', NULL);

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