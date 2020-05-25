package com.practice.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * @author fanShiHao
 * @date 2020/5/15 14:52
 * @description 在这个 Demo 中前端占用的端口是8080，后端是 8088。这就存在跨域的问题，如果不解决的话后端就没法接收前端的请求。 通过配置 Spring MVC 实现了 CORS
 */
public class CORSConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(ALL)
                        .allowedHeaders(ALL)
                        .allowedMethods(ALL)
                        .allowCredentials(true);
            }
        };
    }
}
