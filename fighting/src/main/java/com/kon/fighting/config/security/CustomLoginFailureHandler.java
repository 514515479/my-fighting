package com.kon.fighting.config.security;

import com.kon.fighting.common.dto.Result;
import com.kon.fighting.common.enums.ResultEnum;
import com.kon.fighting.utils.HttpServletUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 认证失败处理
 * @Author LK
 * @Date 2019/10/17 14:30
 * @Version V1.0
 **/
@Component
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        Result error = null;
        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
            error = Result.fail(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
        } else if (e instanceof LockedException) {
            error = Result.fail(ResultEnum.LOCKED);
        } else if (e instanceof CredentialsExpiredException) {
            error = Result.fail(ResultEnum.CREDENTIALS_EXPIRED);
        } else if (e instanceof DisabledException) {
            error = Result.fail(ResultEnum.DISABLED);
        } else {
            error = Result.fail(ResultEnum.LOGIN_FAIL);
        }
        HttpServletUtils.writeResult(response, error);
    }

}
