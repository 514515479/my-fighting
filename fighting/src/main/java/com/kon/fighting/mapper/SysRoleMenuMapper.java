package com.kon.fighting.mapper;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.entity.SysRoleMenu;

import java.util.Set;

/**
 * @Description 角色菜单Mapper
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 根据角色id获取菜单Id集合
     *
     * @return
     */
    Set<Long> selectMenuIdSetByRoleId(Long roleId);

}