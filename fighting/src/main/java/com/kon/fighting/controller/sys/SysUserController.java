package com.kon.fighting.controller.sys;

import com.kon.fighting.common.dto.Result;
import com.kon.fighting.entity.SysUser;
import com.kon.fighting.service.SysUserService;
import com.kon.fighting.utils.FightingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description 用户控制器
 * @Author LK
 * @Date 2019/11/5 9:57
 * @Version V1.0
 **/
@RestController
@RequestMapping("/system/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping
    public Result list(@RequestParam Map<String, Object> map) {
        return Result.okData(sysUserService.findUserPageByMap(map));
    }

    /**
     * 新增
     *
     * @param entity 实体
     * @return
     */
    @PostMapping
    public Result save(@RequestBody SysUser entity) {
        return Result.crud(sysUserService.saveUserAndUserRole(entity));
    }

    /**
     * 修改
     *
     * @param entity 实体
     * @return
     */
    @PutMapping
    public Result update(@RequestBody SysUser entity) {
        return Result.crud(sysUserService.updateUserAndUserRole(entity));
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
        return Result.crud(sysUserService.deleteByIdList(ids));
    }

    /**
     * 修改密码
     *
     * @return
     */
    @PostMapping("/changePassword")
    public Result changePassword(@RequestParam("id") Long id,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("password") String password) {
        return sysUserService.changePassword(id, oldPassword, password);
    }

}
