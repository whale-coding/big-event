package com.star.interceptors;

import com.star.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @Classname: LoginInterceptor
 * @Date: 2024/11/27 20:25
 * @Author: 聂建强
 * @Description: 登录拦截器
 */
@Component  // 将拦截器加入到Spring容器里面
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 重写拦截方法
     * @param request 请求头
     * @param response 响应头
     * @param handler 处理器
     * @return 是否放行
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的token
        String token = request.getHeader("Authorization");
        // token验证
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 如果可以解析token不抛出异常，放行
            return true;
        }catch (Exception e){
            // 捕获异常，说明token验证失败
            response.setStatus(401);  // http响应状态码设置为401
            // 不放行
            return false;
        }
    }
}
