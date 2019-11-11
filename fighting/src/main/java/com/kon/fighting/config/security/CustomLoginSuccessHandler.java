package com.kon.fighting.config.security;

import com.kon.fighting.common.constant.Constant;
import com.kon.fighting.common.constant.CustomStaticValue;
import com.kon.fighting.common.dto.Result;
import com.kon.fighting.common.enums.ResultEnum;
import com.kon.fighting.entity.SuperUser;
import com.kon.fighting.utils.HttpServletUtils;
import com.kon.fighting.utils.IpUtils;
import com.kon.fighting.utils.JwtTokenUtils;
import com.kon.fighting.utils.TokenCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 认证成功处理使用redis
 * @Author LK
 * @Date 2019/10/21 15:35
 * @Version V1.0
 **/
@Slf4j
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenCacheUtils tokenCacheUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
        // 获取请求的ip地址
        String ip = IpUtils.getIpAddress(request);
        Map<String, Object> map = new HashMap<>();
        map.put(Constant.IP, ip);

        SuperUser superUser = (SuperUser) auth.getPrincipal();
        String username = superUser.getUsername();
        String token = JwtTokenUtils.generateTokenBySalt(username, CustomStaticValue.EXPIRATION_SECOND, map);
        String fullToken = Constant.BEARER + token;
        tokenCacheUtils.setTokenRefresh(token, username, ip);
        log.info("用户{}的token为：{}", superUser.getUsername(), fullToken);
        HttpServletUtils.setHeader(response, Constant.AUTHORIZATION, fullToken);
        Result result = Result.ok(ResultEnum.LOGIN_SUCCESS, superUser);
        HttpServletUtils.writeResult(response, result);
    }

}
