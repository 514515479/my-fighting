package com.kon.fighting.config.security;

import com.kon.fighting.common.dto.Result;
import com.kon.fighting.common.enums.ResultEnum;
import com.kon.fighting.utils.HttpServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 用户未登录时返回给前端的数据
 * @Author LK
 * @Date 2019/10/20 19:39
 * @Version V1.0
 **/
@Component
public class CustomNoLoginHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        Result result = Result.ok(ResultEnum.NO_LOGIN);
        HttpServletUtils.writeResultAndChangeStatus(response, result);
    }

}