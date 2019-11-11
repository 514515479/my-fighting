package com.kon.fighting.service;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.common.persistence.BaseServiceImpl;
import com.kon.fighting.entity.SysRole;
import com.kon.fighting.entity.SysRoleMenu;
import com.kon.fighting.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description 角色接口
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
@Service
public class SysRoleService extends BaseServiceImpl<SysRole, Long> {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    protected BaseMapper<SysRole> getMapper() {
        return sysRoleMapper;
    }

    /**
     * 新增角色及角色菜单关系
     *
     * @param entity 角色实体
     * @return
     */
    @Transactional
    public boolean saveRoleAndRoleMenu(SysRole entity) {
        // 新增角色
        int roleInsertNum = this.saveSelective(entity);
        if (roleInsertNum > 0) {
            // 新增角色菜单关系
            int roleMenuInsertNum = this.saveRoleMenu(entity.getId(), entity.getMenuIdList());
            return roleMenuInsertNum > 0;
        }
        return false;
    }

    /**
     * 修改角色及角色菜单关系
     *
     * @param entity 角色实体
     * @return
     */
    @Transactional
    public boolean updateRoleAndRoleMenu(SysRole entity) {
        int roleUpdateNum = this.updateSelective(entity);
        if (roleUpdateNum > 0) {
            // 删除原有的角色菜单关系
            sysRoleMenuService.delete(new SysRoleMenu().setRoleId(entity.getId()));
            // 新增角色菜单关系
            int roleMenuInsertNum = this.saveRoleMenu(entity.getId(), entity.getMenuIdList());
            return roleMenuInsertNum > 0;
        }
        return false;
    }

    /**
     * 新增角色菜单关系
     *
     * @param roleId 角色Id
     * @param menuIdList 菜单权限Id集合
     * @return
     */
    private int saveRoleMenu(Long roleId, Set<Long> menuIdList) {
        List<SysRoleMenu> roleMenuList = new ArrayList<>();
        SysRoleMenu sysRoleMenu;
        for (Long menuId : menuIdList) {
            sysRoleMenu = new SysRoleMenu().setRoleId(roleId).setMenuId(menuId);
            roleMenuList.add(sysRoleMenu);
        }
        return sysRoleMenuService.saveList(roleMenuList);
    }

}
