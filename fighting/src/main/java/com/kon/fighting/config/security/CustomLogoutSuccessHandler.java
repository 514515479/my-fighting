package com.kon.fighting.config.security;

import com.kon.fighting.common.constant.Constant;
import com.kon.fighting.common.dto.Result;
import com.kon.fighting.common.enums.ResultEnum;
import com.kon.fighting.service.SuperUserService;
import com.kon.fighting.utils.HttpServletUtils;
import com.kon.fighting.utils.JwtTokenUtils;
import com.kon.fighting.utils.TokenCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 登出成功处理使用redis
 * @Author LK
 * @Date 2019/10/21 15:46
 * @Version V1.0
 **/
@Slf4j
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private SuperUserService superUserService;

    @Autowired
    private TokenCacheUtils tokenCacheUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        // 获取token
        String authHeader = request.getHeader(Constant.AUTHORIZATION);
        // 如果token存在
        if (authHeader != null && authHeader.startsWith(Constant.BEARER)) {
            final String authToken = authHeader.substring(Constant.BEARER.length());
            // 退出的用户名
            String username = JwtTokenUtils.parseTokenBySalt(authToken);
            if (username != null) {
                // 删除用户信息缓存
                superUserService.clearUserCacheByUsername(username);
            }
            // 将token放入黑名单中
            tokenCacheUtils.addBlackList(authToken);
            // 删除token缓存
            tokenCacheUtils.deleteToken(authToken);
            log.info("token：{}已加入redis黑名单", authToken);
        }
        Result result = Result.ok(ResultEnum.LOGOUT_SUCCESS);
        HttpServletUtils.writeResult(response, result);
    }

}
