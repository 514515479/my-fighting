package com.kon.fighting.service;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.common.persistence.BaseServiceImpl;
import com.kon.fighting.entity.SysRoleMenu;
import com.kon.fighting.mapper.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Description 角色菜单接口
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
@Service
public class SysRoleMenuService extends BaseServiceImpl<SysRoleMenu, Long> {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    protected BaseMapper<SysRoleMenu> getMapper() {
        return sysRoleMenuMapper;
    }

    /**
     * 根据角色id获取菜单Id集合
     *
     * @return
     */
    public Set<Long> selectMenuIdSetByRoleId(Long roleId) {
        return sysRoleMenuMapper.selectMenuIdSetByRoleId(roleId);
    }

}
