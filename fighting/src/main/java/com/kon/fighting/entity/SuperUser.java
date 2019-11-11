package com.kon.fighting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @Description 登录用户
 * @Author LK
 * @Date 2019/11/2 17:35
 * @Version V1.0
 **/
@NoArgsConstructor
@AllArgsConstructor
public class SuperUser extends SysUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Set<SysRole> roles;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 构造SuperUser对象
     *
     * @param sysUser
     * @param authorities
     */
    public SuperUser(SysUser sysUser, Set<SysRole> roles, Collection<? extends GrantedAuthority> authorities) {
        BeanUtils.copyProperties(sysUser, this);
        this.roles = roles;
        this.authorities = authorities;
    }

    /**
     * 角色Set
     *
     * @return
     */
    public Set<SysRole> getRoles() {
        return roles;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 账号是否没过期
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否没锁定
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否没过期
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否启用
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return super.getEnabledFlag();
    }

}
