package com.kon.fighting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description 主启动类
 * @Author LK
 * @Date 2019/10/31 16:46
 * @Version V1.0
 **/
@SpringBootApplication
@EnableCaching  // 开启缓存
@EnableAsync    // 开启异步
@MapperScan("com.kon.fighting.mapper")
public class FightingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FightingApplication.class, args);
    }

}
