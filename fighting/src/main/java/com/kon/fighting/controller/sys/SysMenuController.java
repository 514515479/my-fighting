package com.kon.fighting.controller.sys;

import com.kon.fighting.common.dto.Result;
import com.kon.fighting.common.enums.MenuTypeEnum;
import com.kon.fighting.entity.SuperUser;
import com.kon.fighting.entity.SysMenu;
import com.kon.fighting.service.SysMenuService;
import com.kon.fighting.utils.FightingUtils;
import com.kon.fighting.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * @Description 菜单控制器
 * @Author LK
 * @Date 2019/11/3 13:24
 * @Version V1.0
 **/
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 刷新权限缓存
     */
    @GetMapping("/refreshPermission")
    public Result refreshPermission() {
        sysMenuService.clearAllPermission();
        return Result.ok();
    }

    /**
     * 获取用户左侧菜单树
     *
     * @return
     */
    @GetMapping("/getMenuTree")
    public Result getMenuTree() {
        SuperUser user = SecurityUtils.getUser();
        // 针对fighting的特殊处理
        Long userId = user.getId();
        userId = userId == 1L ? null : userId;
        List<SysMenu> menuTree = sysMenuService.buildMenuTreeByUserId(userId);
        return Result.okData(menuTree);
    }

    /**
     * 获取所有菜单
     */
    @GetMapping("/getAllMenuTree")
    public Result getAllMenuTree() {
        List<SysMenu> menuTree = sysMenuService.findMenuTree(MenuTypeEnum.MENU);
        return Result.okData(menuTree);
    }

    /**
     * 菜单树列表
     *
     * @return
     */
    @GetMapping
    public Result menuTree(@RequestParam Map<String, Object> map) {
        List<SysMenu> menuList;
        if (StringUtil.isNotEmpty((String) map.get("name"))) {
            menuList = sysMenuService.selectMenuByMap(map);
        } else {
            menuList = sysMenuService.findMenuTree(MenuTypeEnum.PERMISSION);
        }
        return Result.okData(menuList);
    }

    /**
     * 新增
     *
     * @param entity 实体
     * @return
     */
    @PostMapping
    public Result save(@RequestBody SysMenu entity) {
        int num = sysMenuService.saveSelective(entity);
        this.clearCache(num, entity);
        return Result.crud(num);
    }

    /**
     * 修改
     *
     * @param entity 实体
     * @return
     */
    @PutMapping
    public Result update(@RequestBody SysMenu entity) {
        int num = sysMenuService.updateSelective(entity);
        this.clearCache(num, entity);
        return Result.crud(num);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Long> ids) {
        boolean res = FightingUtils.checkIds(ids);
        if (!res) {
            return Result.fail("你想干什么？？？");
        }
        int num = sysMenuService.deleteByIdList(ids);
        this.clearCache(num, null);
        return Result.crud(num);
    }

    /**
     * 清除缓存
     *
     * @param num 影响行数
     * @param entity 实体
     */
    private void clearCache(int num, SysMenu entity) {
        if (num > 0) {
            // 清除所有菜单缓存
            sysMenuService.clearAll();
            if (entity == null || MenuTypeEnum.PERMISSION.getCodeValue().equals(entity.getType())) {
                // 如果有新增或修改权限，清除所有权限缓存
                sysMenuService.clearAllPermission();
            }
        }
    }

}
