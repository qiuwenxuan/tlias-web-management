package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*") // 表示对所有请求路径都拦截
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException { // 拦截到请求之后调用，可以调用多次
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 1.获取请求url
        String url = req.getRequestURI().toString();
        log.info("请求的URl:{}", url);

        // 2.判断请求url是否为login，如果是则放行
        if (url.contains("login")) {
            log.info("登录操作，放行...");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 3.获取请求头当中的令牌（token）
        String jwt = req.getHeader("token");

        // 4.判断令牌是否存在，不存在返回错误信息
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录的错误信息！");
            Result error = Result.error("NOT_LOGIN");

            // 解析Result对象为json格式返回到前端
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        // 5.解析token，解析出错返回错误信息
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录的错误信息！");
            Result error = Result.error("NOT_LOGIN");

            // 解析Result对象为json格式返回到前端
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        // 6.令牌合法放行
        log.info("令牌合法，放行...");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}