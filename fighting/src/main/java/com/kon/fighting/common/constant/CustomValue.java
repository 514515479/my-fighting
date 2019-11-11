package com.kon.fighting.common.constant;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Description 自定义值
 * @Author LK
 * @Date 2019/10/17 16:16
 * @Version V1.0
 **/
@Slf4j
@Configuration
@Data
public class CustomValue {

    /**
     * 后缀（比如 ~ , ! 等等）
     */
    @Value("${custom.result.suffix}")
    private String suffix;

    /**
     * token 过期时间
     */
    @Value("${custom.jwt.expirationSecond}")
    private int expirationSecond;

    /**
     * token 刷新时间
     */
    @Value("${custom.jwt.validTime}")
    private int validTime;

    /**
     * 以旧换新有效时长（秒）
     */
    @Value("${custom.jwt.oldChangeNewSecond}")
    private int oldChangeNewSecond;

    /**
     * 文件上传路径
     */
    @Value("${custom.upload.path}")
    private String path;

    /**
     * 静态资源对外暴露的访问路径
     */
    @Value("${custom.upload.staticAccessPath}")
    private String staticAccessPath;

    /**
     * 后缀（比如 ~ , ! 等等）
     */
    @Value("${custom.individualModel}")
    private boolean individualModel;

    @PostConstruct
    public void init() {
        log.info("初始化静态类变量的值。。。");
        CustomStaticValue.init(this);
    }

}
