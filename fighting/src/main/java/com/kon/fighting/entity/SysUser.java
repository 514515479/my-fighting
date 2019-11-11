package com.kon.fighting.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
 * @Description 用户
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table(name = "sys_user")
public class SysUser extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id（备用）
     */
    @Column(name = "dept_id")
    private Long deptId;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "`password`")
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 账号状态（0：禁用   1：启用）
     */
    @Column(name = "enabled_flag")
    private Boolean enabledFlag;

    /**
     * 角色id集合（非数据库字段）
     */
    @Transient
    private Set<Long> roleIdList;

//    /**
//     * 角色英文名称集合（非数据库字段）
//     */
//    @Transient
//    private Set<String> roleNameEnList;
//
//    /**
//     * 角色中文名称集合（非数据库字段）
//     */
//    @Transient
//    private Set<String> roleNameZhList;

}