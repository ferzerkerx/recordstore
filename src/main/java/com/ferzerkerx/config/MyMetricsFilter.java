package com.ferzerkerx.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyMetricsFilter extends OncePerRequestFilter  {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("#######" + httpServletRequest.getServletPath());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        System.out.println("#######" + httpServletResponse.getStatus());

    }
}
