package com.kon.fighting.service;

import com.kon.fighting.common.constant.CustomStaticValue;
import com.kon.fighting.common.enums.MenuTypeEnum;
import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.common.persistence.BaseServiceImpl;
import com.kon.fighting.entity.SysMenu;
import com.kon.fighting.mapper.SysMenuMapper;
import com.kon.fighting.utils.FightingUtils;
import com.kon.fighting.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description 菜单接口
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
@Slf4j
@Service
@CacheConfig(cacheNames = "SysMenuService")
public class SysMenuService extends BaseServiceImpl<SysMenu, Long> {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected BaseMapper<SysMenu> getMapper() {
        return sysMenuMapper;
    }

    /**
     * 根据用户id查找菜单树
     *
     * @param userId 用户id
     * @return
     */
    public List<SysMenu> buildMenuTreeByUserId(Long userId) {
        // 该用户所拥有的菜单集合
        List<SysMenu> sysMenuList;
        if (userId == null) {
            // 这个是fighting用户
            sysMenuList = findAllMenu();
        } else {
            // 根据用户Id查询菜单Id集合
            Set<Long> menuIdSet = sysUserRoleService.selectMenuIdSetByUserId(userId);
            sysMenuList = buildUserMenu(menuIdSet);
        }
        // 构造菜单树
        List<SysMenu> menuTree = FightingUtils.buildMenuTree(sysMenuList);
        return menuTree;
    }

    /**
     * 构造用户菜单集合
     *
     * @param menuIdSet 菜单Id集合
     * @return
     */
    public List<SysMenu> buildUserMenu(Set<Long> menuIdSet) {
        List<SysMenu> sysMenuList = new ArrayList<>();
        Map<Long, SysMenu> allMenu = this.findAllMenuMap();
        SysMenu sysMenu;
        for (Long menuId : menuIdSet) {
            sysMenu = allMenu.get(menuId);
            if (sysMenu != null) {
                sysMenuList.add(sysMenu);
            }
        }
        return sysMenuList;
    }

    /**
     * 查询所有菜单权限(走缓存)
     * 这里没有使用spring cache是因为出现了在方法中嵌套调用缓存方法失效的问题）
     */
    public List<SysMenu> findAll() {
        // 取缓存
        Object data = redisUtils.get("SysMenuService::findAll");
        if (data != null) {
            return (List<SysMenu>) data;
        }
        // 查询数据库
        WeekendSqls<SysMenu> sql = WeekendSqls.custom();
        sql.andEqualTo(SysMenu::getDelFlag, false);
        List<SysMenu> sysMenuList = this.getByExample(Example.builder(SysMenu.class).where(sql).build());
        // 设置缓存
        if (sysMenuList.size() > 0) {
            redisUtils.set("SysMenuService::findAll", sysMenuList, CustomStaticValue.EXPIRATION_SECOND);
        }
        return sysMenuList;
    }

    /**
     * 删除所有菜单权限缓存
     */
    public void clearAll() {
        redisUtils.del("SysMenuService::findAll");
    }

    /**
     * 查询所有菜单(走缓存)
     *
     * @return
     */
    public List<SysMenu> findAllMenu() {
        List<SysMenu> all = this.findAll();
        return all.stream().filter(menu -> !MenuTypeEnum.PERMISSION.getCodeValue().equals(menu.getType())).collect(Collectors.toList());
    }

    /**
     * 查询所有菜单Map(走缓存)
     *
     * @return
     */
    public Map<Long, SysMenu> findAllMenuMap() {
        List<SysMenu> allMenu = this.findAllMenu();
        return allMenu.stream().collect(Collectors.toMap(SysMenu::getId, a -> a, (k1, k2) -> k1));
    }

    /**
     * 查询所有权限
     *
     * @return
     */
    @Cacheable(key = "#root.methodName", unless = "#result == null || #result.size() == 0")
    public List<SysMenu> findAllPermission() {
        return sysMenuMapper.selectAllPermission();
    }

    /**
     * 删除所有权限缓存
     */
    public void clearAllPermission() {
        redisUtils.del("SysMenuService::findAllPermission");
    }

    /**
     * 查询菜单权限树
     *
     * @param menuType 菜单类型枚举
     *                 如果是PERMISSION查全部
     *                 否则查所有菜单
     * @return
     */
    public List<SysMenu> findMenuTree(MenuTypeEnum menuType) {
        List<SysMenu> sysMenuList;
        if (MenuTypeEnum.PERMISSION.equals(menuType)) {
            sysMenuList = this.findAll();
        } else {
            sysMenuList = this.findAllMenu();
        }
        // 构造菜单权限树
        List<SysMenu> menuTree = FightingUtils.buildMenuTree(sysMenuList);
        return menuTree;
    }

    /**
     * 根据菜单名查询菜单
     *
     * @param map
     * @return
     */
    public List<SysMenu> selectMenuByMap(Map<String, Object> map) {
        return sysMenuMapper.selectMenuByMap(map);
    }

}
