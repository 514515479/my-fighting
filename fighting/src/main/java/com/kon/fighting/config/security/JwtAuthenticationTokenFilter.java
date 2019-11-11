package com.kon.fighting.config.security;

import com.kon.fighting.common.constant.Constant;
import com.kon.fighting.common.constant.CustomStaticValue;
import com.kon.fighting.common.dto.Result;
import com.kon.fighting.common.enums.ResultEnum;
import com.kon.fighting.service.SuperUserService;
import com.kon.fighting.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 确保在一次请求只通过一次filter，而不需要重复执行
 * @Author LK
 * @Date 2019/10/20 20:48
 * @Version V1.0
 **/
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private SuperUserService superUserService;

    @Autowired
    private TokenCacheUtils tokenCacheUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 获取token
        String authHeader = request.getHeader(Constant.AUTHORIZATION);
        // 获取请求的ip地址
        String currentIp = IpUtils.getIpAddress(request);
        // 如果token存在
        if (authHeader != null && authHeader.startsWith(Constant.BEARER)) {
            String authToken = authHeader.substring(Constant.BEARER.length());

            // 获取token中的subject
            String username = JwtTokenUtils.parseTokenBySalt(authToken);
            // token中的ip信息
            String ip = CollectionUtils.getMapValue(JwtTokenUtils.getClaimsBySalt(authToken), Constant.IP);

            // 进入黑名单验证
            if (tokenCacheUtils.isBlackList(authToken)) {
                log.info("用户：{}的token：{}在黑名单之中，拒绝访问", username, authToken);
                // 这里有一种特殊情况，如果token过期了，但是发起了多个请求，新token只给了一个，所以这里还要判断是否有新token
                String newToken = tokenCacheUtils.getNewTokenByOldRetry(authToken);
                if (newToken == null) {
                    HttpServletUtils.writeResultAndChangeStatus(response, Result.ok(ResultEnum.TOKEN_IS_BLACKLIST));
                    return;
                } else {
                    // 更新基本参数
                    authToken = newToken;
                    username = JwtTokenUtils.parseTokenBySalt(authToken);
                    ip = CollectionUtils.getMapValue(JwtTokenUtils.getClaimsBySalt(authToken), Constant.IP);
                    HttpServletUtils.setHeader(response, Constant.AUTHORIZATION, Constant.BEARER + newToken);
                }
            }

            /**
             * 判断token是否过期
             * 过期的话，从redis中读取有效时间（比如七天登录有效），再refreshToken（根据以后业务加入，现在直接refresh）
             * 同时，已过期的token加入黑名单
             */
            // 判断redis中是否有保存
            if (tokenCacheUtils.hasToken(authToken)) {
                String expirationTime = tokenCacheUtils.getExpirationTimeByToken(authToken).toString();
                // 判断token是否过期
                if (DateUtils.isExpiration(expirationTime)) {
                    tokenCacheUtils.addBlackList(authToken);
                    log.info("已作废，加入黑名单{}", authToken);
                    // 获得redis中用户的token刷新时效
                    String tokenValidTime = (String) tokenCacheUtils.getTokenValidTimeByToken(authToken);
                    // 判断刷新时间是否有效
                    if (DateUtils.isExpiration(tokenValidTime)) {
                        log.info("{}已超过刷新时间有效期，不予刷新", authToken);
                        HttpServletUtils.writeResultAndChangeStatus(response, Result.ok(ResultEnum.LOGIN_IS_OVERDUE));
                        return;
                    } else {
                        // 仍在刷新时间内，则刷新token，放入请求头中(这里之所以取缓存中获取,因为token过期之后jwt是反解析不到数据的)

                        // 更新username
                        username = tokenCacheUtils.getUsernameByToken(authToken).toString();
                        // 更新ip
                        ip = tokenCacheUtils.getIpByToken(authToken).toString();

                        Map<String, Object> map = new HashMap<>();
                        map.put(Constant.IP, ip);
                        String jwtToken = JwtTokenUtils.generateTokenBySalt(username, CustomStaticValue.EXPIRATION_SECOND, map);

                        tokenCacheUtils.deleteToken(authToken);
                        tokenCacheUtils.setTokenRefresh(jwtToken, username, ip);
                        tokenCacheUtils.setOldChangeNew(authToken, jwtToken);
                        log.info("redis已删除-旧token：{}", authToken);
                        log.info("redis已更新-新token：{}", jwtToken);

                        // 更新token，为了后面使用
                        authToken = jwtToken;
                        HttpServletUtils.setHeader(response, Constant.AUTHORIZATION, Constant.BEARER + jwtToken);
                    }
                }
            }

            // 登录凭证
            Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
            if (username != null && currentAuth == null) {
                // 加入对ip的验证,如果ip不正确，进入黑名单验证
                if (!ip.equals(currentIp)) {
                    log.info("用户：{}的ip地址变动，进入黑名单校验", username);
                    // 进入黑名单验证
                    if (tokenCacheUtils.isBlackList(authToken)) {
                        log.info("用户：{}的token：{}在黑名单之中，拒绝访问", username, authToken);
                        HttpServletUtils.writeResultAndChangeStatus(response, Result.ok(ResultEnum.TOKEN_IS_BLACKLIST));
                        return;
                    }
                    // 黑名单没有则继续，如果黑名单存在就退出后面
                }
            } else if (username == null && currentAuth == null) {
                // 这里可以直接告知用户：无效token
                HttpServletUtils.writeResultAndChangeStatus(response, Result.ok(ResultEnum.TOKEN_IS_BLACKLIST));
                return;
            }

            // 调用接口获取用户信息
            UserDetails userDetails = superUserService.loadUserByUsername(username);
            // 获取到用户信息
            if (userDetails != null) {
                // 生成UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 设置到上下文中
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}
