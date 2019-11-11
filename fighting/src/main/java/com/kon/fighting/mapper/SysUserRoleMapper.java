package com.kon.fighting.mapper;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.entity.SysUserRole;

import java.util.List;
import java.util.Set;

/**
 * @Description 用户角色Mapper
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 根据用户id获取菜单Id集合
     *
     * @param userId
     * @return
     */
    Set<Long> selectMenuIdSetByUserId(Long userId);

    /**
     * 根据用户Id获取用户角色List
     *
     * @param userId 用户Id
     * @return
     */
    List<SysUserRole> selectUserRoleListByUserId(Long userId);

}