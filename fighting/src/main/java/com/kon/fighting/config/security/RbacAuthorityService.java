package com.kon.fighting.config.security;

import com.kon.fighting.common.constant.Constant;
import com.kon.fighting.entity.SuperUser;
import com.kon.fighting.entity.SysMenu;
import com.kon.fighting.entity.SysRole;
import com.kon.fighting.service.SysMenuService;
import com.kon.fighting.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Description 权限访问控制
 * @Author LK
 * @Date 2019/10/20 20:37
 * @Version V1.0
 **/
@Slf4j
@Component("rbacAuthorityService")
public class RbacAuthorityService {

    @Autowired
    private SysMenuService sysMenuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        // 请求的路径
        String requestURI = request.getRequestURI();
        // 请求的方法
        String requestMethod = request.getMethod();
        log.info("请求的url是{}", requestURI);
        log.info("请求的方式是{}", requestMethod);
        log.info("----------------分割线----------------");
        // 判断是否是开放接口请求,是的话默认放过
        if (antPathMatcher.match("/open/**", requestURI)) {
            return true;
        }

        Object userInfo = authentication.getPrincipal();
        if (userInfo instanceof UserDetails) {
            // 当前登录用户
            SuperUser user = (SuperUser) userInfo;
            // 用户的角色集合
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            // 判断用户是否是fighting,默认拥有所有权限
            if (Constant.FIGHTING.equals(user.getUsername())) {
                return true;
            }
            Collection<? extends GrantedAuthority> permissionRole = getPermissionRole(requestURI, requestMethod);
            if (permissionRole != null) {
                String needRole;
                for (GrantedAuthority permissionAuthority : permissionRole) {
                    needRole = permissionAuthority.getAuthority();
                    // Constant.ROLE_LOGIN为登录即可访问
                    if (Constant.ROLE_LOGIN.equals(needRole)) {
                        return true;
                    }
                    for (GrantedAuthority userAuthority : authorities) {
                        if (needRole.equals(userAuthority.getAuthority())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private Collection<? extends GrantedAuthority> getPermissionRole(String requestURI, String requestMethod) {
        // 所有权限
        List<SysMenu> allPermission = sysMenuService.findAllPermission();
        for (SysMenu sysMenu : allPermission) {
            if (antPathMatcher.match(sysMenu.getUrl(), requestURI)
                    && sysMenu.getMethod().equals(requestMethod)
                    && sysMenu.getRoles().size() > 0) {
                Set<SysRole> roles = sysMenu.getRoles();
                return SecurityUtils.getUserAuthorities(roles);
            }
        }
        // 没有匹配的url，只要登录即可访问
        // return AuthorityUtils.createAuthorityList(Constant.ROLE_LOGIN);
        // 没有匹配的url，说明没有权限
        return null;
    }

}
