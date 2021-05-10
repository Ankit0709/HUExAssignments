package com.hashedin.assignments.NetflixRestApiAssignment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Component
public class TimingMiddleWare implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimingMiddleWare.class);

    Instant startTime,endTime;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        startTime = Instant.now();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        endTime = Instant.now();
        long time = Duration.between(startTime,endTime).toMillis();
        LOGGER.info("Time Taken to Execute the request :"+time+" milliseconds .");
        response.setHeader("X-TIME-TO-EXECUTE", String.valueOf(time));
    }
}
