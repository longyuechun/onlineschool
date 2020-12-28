package com.rulaifozu.onlineschool.config.jwtutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Autowired
    private LoginUserHandler loginUserHandler;

    @Autowired
    private LoginAdminHandler loginAdminHandler;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
//                        "你的登陆路径",
                        "/login",
                        "/register",
                        "/*.html",            //html静态资源
                        "/*.js",              //js静态资源
                        "/*.css"              //css静态资源
                         );
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandler);

        //是不是可以在这里增加loginAdminHanderler
        //argumentResolvers.add(loginAdminHandler);
    }
}
