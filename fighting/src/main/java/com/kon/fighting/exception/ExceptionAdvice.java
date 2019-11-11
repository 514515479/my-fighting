package com.kon.fighting.exception;

import com.kon.fighting.common.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 全局异常处理类
 * @Author LK
 * @Date 2019/10/17 8:38
 * @Version V1.0
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * Exception异常全局处理
     *
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandle(Exception e) {
        // 打印错误堆栈
        e.printStackTrace();
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}
