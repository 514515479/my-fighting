package com.kon.fighting.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Description 角色菜单
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "sys_role_menu")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 菜单id
     */
    @Column(name = "menu_id")
    private Long menuId;
}