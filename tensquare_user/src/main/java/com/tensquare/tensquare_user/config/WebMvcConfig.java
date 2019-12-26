package com.tensquare.tensquare_user.config;


import com.tensquare.tensquare_user.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * WebMvcConfigurerAdapter已经过时,新的版本解决方案目前有两种:
 * 1.实现WebMvcConfigurer
 * 2.继承WebMvcConfigurationSupport
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
    }
}
