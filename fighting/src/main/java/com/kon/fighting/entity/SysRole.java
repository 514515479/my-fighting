package com.kon.fighting.entity;

import com.kon.fighting.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Set;

/**
 * @Description 角色
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table(name = "sys_role")
public class SysRole extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色英文名称
     */
    @Column(name = "name_en")
    private String nameEn;

    /**
     * 角色中文名称
     */
    @Column(name = "name_zh")
    private String nameZh;

    /**
     * 角色介绍
     */
    @Column(name = "role_desc")
    private String roleDesc;

    /**
     * 菜单权限id集合（非数据库字段）
     */
    @Transient
    private Set<Long> menuIdList;

}