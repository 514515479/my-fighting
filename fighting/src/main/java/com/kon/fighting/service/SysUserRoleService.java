package com.kon.fighting.service;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.common.persistence.BaseServiceImpl;
import com.kon.fighting.entity.SysUserRole;
import com.kon.fighting.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Description 用户角色接口
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
@Service
public class SysUserRoleService extends BaseServiceImpl<SysUserRole, Long> {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    protected BaseMapper<SysUserRole> getMapper() {
        return sysUserRoleMapper;
    }

    /**
     * 根据用户id获取菜单Id集合
     *
     * @return
     */
    public Set<Long> selectMenuIdSetByUserId(Long userId) {
        return sysUserRoleMapper.selectMenuIdSetByUserId(userId);
    }

    /**
     * 根据用户Id获取用户角色List
     *
     * @param userId 用户Id
     * @return
     */
    public List<SysUserRole> selectUserRoleListByUserId(Long userId) {
        return sysUserRoleMapper.selectUserRoleListByUserId(userId);
    }

}
