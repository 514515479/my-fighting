package com.kon.fighting.service;

import com.kon.fighting.common.dto.Page;
import com.kon.fighting.common.dto.Result;
import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.common.persistence.BaseServiceImpl;
import com.kon.fighting.entity.SuperUser;
import com.kon.fighting.entity.SysRole;
import com.kon.fighting.entity.SysUser;
import com.kon.fighting.entity.SysUserRole;
import com.kon.fighting.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description 角色接口
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
@Service
public class SysUserService extends BaseServiceImpl<SysUser, Long> {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 密码编码器
     */
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected BaseMapper<SysUser> getMapper() {
        return sysUserMapper;
    }

    /**
     * 根据用户id获取角色Set
     *
     * @param userId 用户Id
     * @return
     */
    public Set<SysRole> findRoleSetByUserId(Long userId) {
        return sysUserRoleService.selectUserRoleListByUserId(userId)
                .stream()
                .map(sysUserRole -> {
                    SysRole sysRole = new SysRole();
                    sysRole.setNameEn(sysUserRole.getRoleNameEn())
                            .setNameZh(sysUserRole.getRoleNameZh())
                            .setId(sysUserRole.getRoleId());
                    return sysRole;
                })
                .collect(Collectors.toSet());
    }

    /**
     * 查询用户分页list
     *
     * @param map 条件map
     * @return
     */
    public Page<SuperUser> findUserPageByMap(Map<String, Object> map) {
        List<SuperUser> superUserList = sysUserMapper.selectUserPageByMap(map);
        return Page.pageData(superUserList);
    }

    /**
     * 新增用户及用户角色关系
     *
     * @param entity 用户实体
     * @return
     */
    @Transactional
    public boolean saveUserAndUserRole(SysUser entity) {
        String password = entity.getPassword();
        entity.setPassword(passwordEncoder.encode(password));
        // 新增用户
        int userInsertNum = this.saveSelective(entity);
        if (userInsertNum > 0) {
            // 新增用户角色关系
            int userRoleInsertNum = this.saveUserRole(entity.getId(), entity.getRoleIdList());
            return userRoleInsertNum > 0;
        }
        return false;
    }

    /**
     * 修改用户及用户角色关系
     *
     * @param entity 用户实体
     * @return
     */
    @Transactional
    public boolean updateUserAndUserRole(SysUser entity) {
        int userUpdateNum = this.updateSelective(entity);
        if (userUpdateNum > 0) {
            // 删除原有的用户角色关系
            sysUserRoleService.delete(new SysUserRole().setUserId(entity.getId()));
            // 新增用户角色关系
            int userRoleInsertNum = this.saveUserRole(entity.getId(), entity.getRoleIdList());
            return userRoleInsertNum > 0;
        }
        return false;
    }

    /**
     * 新增用户角色关系
     *
     * @param userId     用户Id
     * @param roleIdList 角色Id集合
     * @return
     */
    private int saveUserRole(Long userId, Set<Long> roleIdList) {
        List<SysUserRole> userRoleList = new ArrayList<>();
        SysUserRole sysUserRole;
        for (Long roleId : roleIdList) {
            sysUserRole = new SysUserRole().setUserId(userId).setRoleId(roleId);
            userRoleList.add(sysUserRole);
        }
        return sysUserRoleService.saveList(userRoleList);
    }

    /**
     * 修改密码
     *
     * @param id       主键
     * @param password 新密码
     * @return
     */
    public Result changePassword(Long id, String oldPassword, String password) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if (passwordEncoder.matches(oldPassword, sysUser.getPassword())) {
            SysUser user = new SysUser();
            user.setId(id);
            user.setPassword(passwordEncoder.encode(password));
            int updateNum = updateSelective(user);
            if (updateNum > 0) {

                return Result.crud(true);
            } else {
                return Result.crud(false);
            }
        } else {
            return Result.fail("您输入的旧密码不正确");
        }
    }

}
