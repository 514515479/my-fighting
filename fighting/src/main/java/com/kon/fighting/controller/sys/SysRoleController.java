package com.kon.fighting.controller.sys;

import com.kon.fighting.common.dto.Page;
import com.kon.fighting.common.dto.Result;
import com.kon.fighting.entity.SysRole;
import com.kon.fighting.service.SysRoleMenuService;
import com.kon.fighting.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;
import java.util.Map;

/**
 * @Description 角色控制器
 * @Author LK
 * @Date 2019/11/5 13:16
 * @Version V1.0
 **/
@RestController
@RequestMapping("/system/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 获取所有角色
     *
     * @return
     */
    @GetMapping("/getAllRole")
    public Result getAllRole() {
        return Result.okData(sysRoleService.getAll());
    }

    /**
     * 列表
     *
     * @return
     */
    @GetMapping
    public Result list(@RequestParam Map<String, Object> map,
                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        Page<SysRole> pageList;
        String nameZh = (String) map.get("nameZh");
        if (!StringUtils.isEmpty(nameZh)) {
            WeekendSqls<SysRole> sql = WeekendSqls.custom();
            sql.andLike(SysRole::getNameZh, "%" + nameZh + "%");
            Example example = Example.builder(SysRole.class).where(sql).build();
            pageList = sysRoleService.getPageByExample(pageNum, pageSize, example);
        } else {
            pageList = sysRoleService.getPageByExample(pageNum, pageSize, null);
        }
        return Result.okData(pageList);
    }

    /**
     * 新增
     *
     * @param entity 实体
     * @return
     */
    @PostMapping
    public Result save(@RequestBody SysRole entity) {
        return Result.crud(sysRoleService.saveRoleAndRoleMenu(entity));
    }

    /**
     * 修改
     *
     * @param entity 实体
     * @return
     */
    @PutMapping
    public Result update(@RequestBody SysRole entity) {
        return Result.crud(sysRoleService.updateRoleAndRoleMenu(entity));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Long> ids) {
        return Result.crud(sysRoleService.deleteByIdList(ids));
    }

    /**
     * 根据角色id获取菜单权限集合
     *
     * @param roleId
     * @return
     */
    @GetMapping("/getRoleMenuIdList")
    public Result getMenuIdList(@RequestParam("id") Long roleId) {
        return Result.okData(sysRoleMenuService.selectMenuIdSetByRoleId(roleId));
    }

}
