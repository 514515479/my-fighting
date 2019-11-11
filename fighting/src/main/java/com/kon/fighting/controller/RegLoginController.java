package com.kon.fighting.controller;

import com.kon.fighting.common.dto.Result;
import com.kon.fighting.common.enums.ResultEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 重定向登录控制器
 * @Author LK
 * @Date 2019/10/16 10:44
 * @Version V1.0
 **/
@RestController
public class RegLoginController {

    /**
     * 如果没有指定该url，会被重定向到/error
     */
    @GetMapping("/loginPage")
    public Result login() {
        return Result.ok(ResultEnum.NO_LOGIN);
    }

}
