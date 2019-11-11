package com.kon.fighting.config.web;

import com.kon.fighting.common.constant.Constant;
import com.kon.fighting.common.constant.CustomStaticValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description webMvc相关配置
 * @Author LK
 * @Date 2019/10/22 9:58
 * @Version V1.0
 **/
@Slf4j
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 配置跨域
     *
     * @param registry
     */
    /*@Override
    open void addCorsMappings(CorsRegistry registry) {
        registry
                // 配置可以被跨域的路径
                .addMapping("/**")
                // 允许所有的请求方法访问该跨域资源服务器
                .allowedMethods("*")
                // 允许所有的请求域名访问我们的跨域资源(到时候这里要配置)
                .allowedOrigins("*")
                // 允许所有的请求header访问
                .allowedHeaders("*")
                // 是否允许用户发送、处理 cookie
                .allowCredentials(true)
                // 预检请求的有效期，单位为秒。有效期内，不会重复发送预检请求
                .maxAge(3600);
    }*/

    /**
     * 配置静态资源暴露
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(CustomStaticValue.STATIC_ACCESS_PATH)
                .addResourceLocations(Constant.FILE + CustomStaticValue.PATH);
        log.info("暴露{}下的静态资源,映射目录为{}", Constant.FILE + CustomStaticValue.PATH, CustomStaticValue.STATIC_ACCESS_PATH);
    }

}
