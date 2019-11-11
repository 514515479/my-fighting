package com.kon.fighting.config.security;

import com.kon.fighting.common.constant.CustomStaticValue;
import com.kon.fighting.service.SuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description Spring Security配置类
 * @Author LK
 * @Date 2019/11/2 19:29
 * @Version V1.0
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomNoLoginHandler customNoLoginHandler;

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Autowired
    private CustomLoginFailureHandler customLoginFailureHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Autowired
    private SuperUserService superUserService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private TestFilter testFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(superUserService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 去除csrf（禁用 Spring Security 自带的跨域处理）
        http.csrf().disable()
                // 允许跨域
                .cors()
                .and()
                // 使用JWT,关闭HttpSession
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
                // AccessDeniedHandler 用来解决认证过的用户访问无权限资源时的异常
                .httpBasic().authenticationEntryPoint(customNoLoginHandler)

                .and()
                .authorizeRequests()
                // 忽略的url
                .antMatchers(
                        "/favicon.ico",
                        "/static/**",
                        CustomStaticValue.STATIC_ACCESS_PATH,
                        "/index.html",
                        "/loginPage")
                .permitAll()

                .anyRequest()
                // rbac动态url认证
                .access("@rbacAuthorityService.hasPermission(request,authentication)")

                .and()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/login")
                .successHandler(customLoginSuccessHandler)
                .failureHandler(customLoginFailureHandler)
                .permitAll()

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll();

        // 无权访问 JSON 格式的数据
        http.exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);

        // 这里的优先级是：先进先来
        // JWT Filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 测试 Filter
        //http.addFilterBefore(testFilter, UsernamePasswordAuthenticationFilter.class);

    }

}