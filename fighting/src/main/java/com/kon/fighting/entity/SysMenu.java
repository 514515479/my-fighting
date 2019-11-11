package com.kon.fighting.entity;

import com.kon.fighting.common.persistence.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Description 菜单
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table(name = "sys_menu")
public class SysMenu extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 权限url（正则模式）
     */
    @Column(name = "url")
    private String url;

    /**
     * 请求类型（GET   POST   PUT   DELETE）
     */
    @Column(name = "`method`")
    private String method;

    /**
     * 前端跳转URL
     */
    @Column(name = "`path`")
    private String path;

    /**
     * 菜单组件
     */
    @Column(name = "component")
    private String component;

    /**
     * 父菜单ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 菜单类型（0：目录   1：菜单   2：权限）
     */
    @Column(name = "`type`")
    private String type;

    /**
     * 是否外部链接（0：不是   1：是）
     */
    @Column(name = "external_link")
    private Boolean externalLink;

    /**
     * 菜单等级（非数据库字段）
     */
    @Transient
    private Integer level;

    /**
     * 父菜单名称（非数据库字段）
     */
    @Transient
    private String parentName;

    /**
     * 子菜单（非数据库字段）
     */
    @Transient
    private List<SysMenu> children;

    /**
     * 拥有该权限的角色（非数据库字段）
     */
    @Transient
    private Set<SysRole> roles;

}