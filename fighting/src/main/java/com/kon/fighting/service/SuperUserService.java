package com.kon.fighting.service;

import com.kon.fighting.entity.SuperUser;
import com.kon.fighting.entity.SysRole;
import com.kon.fighting.entity.SysUser;
import com.kon.fighting.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * @Description Spring Security必须实现的接口
 * @Author LK
 * @Date 2019/11/2 17:52
 * @Version V1.0
 **/
@Slf4j
@Service
@CacheConfig(cacheNames = "SuperUserService")
public class SuperUserService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;


    @Cacheable(key = "#username", unless = "#result == null")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser condition = new SysUser();
        condition.setUsername(username).setDelFlag(false);
        SysUser sysUser = sysUserService.getOne(condition);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        Set<SysRole> roleSet = getRoleSet(sysUser.getId());
        Collection<? extends GrantedAuthority> authorities = SecurityUtils.getUserAuthorities(roleSet);
        return new SuperUser(sysUser, roleSet, authorities);
    }


    /**
     * 删除用户信息缓存
     *
     * @param username
     */
    @CacheEvict(key = "#username", allEntries = true, beforeInvocation = true)
    public void clearUserCacheByUsername(String username) {
        log.info("清除{}用户信息缓存", username);
    }


    /**
     * 根据用户Id获取角色Set
     *
     * @param userId 用户id
     * @return
     */
    private Set<SysRole> getRoleSet(Long userId) {
        return sysUserService.findRoleSetByUserId(userId);
    }

}
