package com.itheima.filter;

import jakarta.servlet.*;

import java.io.IOException;

// @WebFilter(urlPatterns = "/login") 表示拦截单个请求
// @WebFilter(urlPatterns = "/depts/*") 表示拦截该路劲下的所有请求
//@WebFilter(urlPatterns = "/*") // 表示对所有请求路径都拦截
public class DemoBFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { // 初始化方法，默认实现，只调用一次
        System.out.println("拦截器初始化方法！");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException { // 拦截到请求之后调用，可以调用多次
        // 放行前逻辑
        System.out.println("DemoBFilter拦截到了请求！");
        System.out.println("DemoBFilter放行前逻辑！");

        // 请求放行
        filterChain.doFilter(servletRequest, servletResponse);

        // 放行后逻辑
        System.out.println("DemoBFilter放行后逻辑！");
    }

    @Override
    public void destroy() { // 销毁方法，默认实现，只调用一次
        System.out.println("拦截器销毁方法！");
        Filter.super.destroy();
    }
}