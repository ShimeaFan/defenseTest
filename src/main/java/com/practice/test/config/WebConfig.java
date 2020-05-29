package com.practice.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * @author fanShiHao
 * @date 2020/5/29 15:10
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置swagger静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("static/**").addResourceLocations("/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }

    /**
     * 跨域配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ALL)
                .allowedHeaders(ALL)
                .allowedMethods(ALL)
                .allowCredentials(true);
    }
}
