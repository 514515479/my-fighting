package com.kon.fighting.exception;

import com.kon.fighting.common.dto.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 处理404
 * @Author LK
 * @Date 2019/10/23 11:18
 * @Version V1.0
 **/

@RestController
public class NotFoundException implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = {"/error"})
    public Result error(HttpServletRequest request) {
        return Result.fail(HttpStatus.NOT_FOUND.value(), "没有对应的响应。。。");
    }

}
