package com.kon.fighting.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Description 用户角色
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "sys_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 角色英文名称（非数据库字段）
     */
    @Transient
    private String roleNameEn;

    /**
     * 角色中文名称（非数据库字段）
     */
    @Transient
    private String roleNameZh;

}