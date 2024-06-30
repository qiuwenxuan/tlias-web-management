package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器Interceptor
 **/
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override // 目标资源方法运行前运行，返回true表示放行，false表示不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandle...");

        // 1.获取请求url
        String url = request.getRequestURI().toString();
        log.info("请求的URl:{}", url);

        // 2.判断请求url是否为login，如果是则放行
        if (url.contains("login")) {
            log.info("登录操作，放行...");
            return true;
        }

        // 3.获取请求头当中的令牌（token）
        String jwt = request.getHeader("token");
        log.info("请求头token值{}:", jwt);

        // 4.判断令牌是否存在，不存在返回错误信息
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录的错误信息！");
            Result error = Result.error("NOT_LOGIN");

            // 解析Result对象为json格式返回到前端
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
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
            response.getWriter().write(notLogin);
            return false;
        }

        // 6.令牌合法放行
        log.info("令牌合法，放行...");
        return true;
    }

    @Override // 目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override // 视图渲染完毕后运行，为最后运行的方法
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
