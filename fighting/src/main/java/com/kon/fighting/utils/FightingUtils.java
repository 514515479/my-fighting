package com.kon.fighting.utils;

import com.kon.fighting.entity.SysMenu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Description 本工程特有工具类
 * @Author LK
 * @Date 2019/11/3 11:24
 * @Version V1.0
 **/
public class FightingUtils {

    /**
     * 构造菜单树
     *
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildMenuTree(List<SysMenu> sysMenuList) {
        // 构造菜单树
        List<SysMenu> menuTree = new ArrayList<>();
        sysMenuList.forEach(menu -> {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                menu.setParentName("我是顶级");
                menuTree.add(menu);
            }
        });
        menuTree.sort(Comparator.comparing(SysMenu::getSort));
        FightingUtils.findChildrenMenu(menuTree, sysMenuList);
        return menuTree;
    }

    /**
     * 遍历子集菜单
     *
     * @param menuTree 当前层级菜单
     * @param menuList 拥有权限的菜单
     */
    public static void findChildrenMenu(List<SysMenu> menuTree, List<SysMenu> menuList) {
        for (SysMenu menu : menuTree) {
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu sysMenu : menuList) {
                if (menu.getId().equals(sysMenu.getParentId())) {
                    sysMenu.setLevel(menu.getLevel() + 1);
                    sysMenu.setParentName(menu.getName());
                    children.add(sysMenu);
                }
            }
            children.sort(Comparator.comparing(SysMenu::getSort));
            menu.setChildren(children);
            findChildrenMenu(children, menuList);
        }
    }

}
