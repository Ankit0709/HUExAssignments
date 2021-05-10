package com.hashedin.assignments.NetflixRestApiAssignment.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tvshows/*")
public class AuthTokenMiddleWare implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("X-Auth-Token");

        if(token == null){
            //token is not present return HTTP 401 error code
            httpServletResponse.setStatus(401);
        }
        else{
            filterChain.doFilter(request,response);
        }
    }
}
