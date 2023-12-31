package org.jack.train.member.config;

import jakarta.annotation.Resource;
import org.jack.common.interceptor.LogInterceptor;
import org.jack.common.interceptor.MemberInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
    @Resource
    MemberInterceptor memberInterceptor;

    @Resource
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                        .addPathPatterns("/**");

        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/member/sendCode", "/member/login");

    }
}
