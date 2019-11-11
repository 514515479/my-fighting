package com.kon.fighting.config.security;

import com.kon.fighting.common.dto.Result;
import com.kon.fighting.common.enums.ResultEnum;
import com.kon.fighting.utils.HttpServletUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description AccessDeniedHandler 用来解决认证过的用户访问无权限资源时的异常
 * @Author LK
 * @Date 2019/10/17 11:07
 * @Version V1.0
 **/
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException, ServletException {

        Result error = Result.fail(ResultEnum.NO_ACCESS);
        HttpServletUtils.writeResult(response, error);

    }

}
