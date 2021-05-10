package com.hashedin.assignments.NetflixRestApiAssignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
class ApplicationConfiguration implements WebMvcConfigurer {

    @Autowired
    private TimingMiddleWare timingMiddleWare;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timingMiddleWare);
    }
}
