package com.mu.muses.config;

import com.mu.muses.intercceptor.JWTInterceptor;
import com.mu.muses.intercceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/account/login")
                .excludePathPatterns("/swagger**/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v3/**")
                .excludePathPatterns("/doc.html");

//        registry.addInterceptor(new PermissionInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/api/account/login")
//                .excludePathPatterns("/api/account/login/**")
//                .excludePathPatterns("/swagger**/**")
//                .excludePathPatterns("/webjars/**")
//                .excludePathPatterns("/v3/**")
//                .excludePathPatterns("/doc.html");
    }

}