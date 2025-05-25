package com.bb.blog.config;


import com.bb.blog.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/api/**") // 指定需要拦截的路径，例如 /api/** 下的所有请求
//                .excludePathPatterns(
//                        "/api/auth/login",     // 登录接口
//                        "/api/auth/register",  // 注册接口
//                        "/error"               // Spring Boot 错误处理路径
//                        // ... 其他不需要 Token 的公共路径
//                );
    }
}