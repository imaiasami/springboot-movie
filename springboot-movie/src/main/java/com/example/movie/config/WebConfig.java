package com.example.movie.config;


import com.example.movie.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        String[] excludePathPatterns = {
                "/",
                "/members/joinMember",
                "/members/loginMember",
                "/members/logout",
                "/error",
                "/css/**",
                "/js/**",
                "*.ico"
        };

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatterns);
    }
}
