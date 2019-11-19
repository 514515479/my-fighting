package com.kon.fighting.entity;

import com.kon.fighting.common.persistence.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Description 留言板实体类
 * @Author LK
 * @Date 2019/11/18 8:55
 * @Version V1.0
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table(name = "blog_msg_board")
public class BlogMsgBoard extends BaseEntity<Long> implements Serializable {

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 留言ip
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 留言内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 节点颜色
     */
    @Column(name = "color")
    private String color;

    /**
     * 节点图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 父级id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 昵称（非数据库字段）
     */
    @Transient
    private String nickname;

    /**
     * 回复List
     */
    @Transient
    private List<BlogMsgBoard> replyList;

    /**
     * 这个实体的备注作为楼层号
     */

}