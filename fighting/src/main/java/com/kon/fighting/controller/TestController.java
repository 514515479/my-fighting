package com.kon.fighting.controller;

import com.kon.fighting.common.dto.Result;
import com.kon.fighting.entity.Test;
import com.kon.fighting.service.TestService;
import com.kon.fighting.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 测试控制器
 * @Author LK
 * @Date 2019/10/31 17:05
 * @Version V1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/hello")
    public Result hello() {
        return Result.ok("hello");
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Long id) {
        Test test = testService.getById(id);
        return Result.okData(test);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Test test) {
        test.setName(test.getName() + test.getId());
        int num = testService.save(test);
        return Result.okData(num);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Test test) {
        int num = testService.update(test);
        return Result.okData(num);
    }

    @DeleteMapping("/delete")
    public Result delete(Long id) {
        int num = testService.deleteById(id);
        return Result.okData(num);
    }

    @GetMapping("/redis/get")
    public Result get(String key) {
        Object data = redisUtils.get(key);
        return Result.okData(data);
    }

    @PostMapping("/redis/set")
    public Result set(String key, String value) {
        Object res = redisUtils.set(key, value);
        return Result.okData(res);
    }

}
