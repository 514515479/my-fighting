package com.kon.fighting.utils;

import com.kon.fighting.entity.SuperUser;
import com.kon.fighting.entity.SysRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description 获取登录用户相关工具类
 * @Author LK
 * @Date 2019/11/3 11:19
 * @Version V1.0
 **/
public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     **/
    public static SuperUser getUser() {
        return (SuperUser) getAuthentication().getPrincipal();
    }

    /**
     * 根据角色Set获取角色英文名称集合
     *
     * @param roleSet 角色Set
     * @return
     */
    public static Collection<? extends GrantedAuthority> getUserAuthorities(Set<SysRole> roleSet) {
        Set<String> roleNameEnSet = roleSet.stream().map(SysRole::getNameEn).collect(Collectors.toSet());
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleNameEnSet.toArray(new String[0]));
        return authorities;
    }

}
