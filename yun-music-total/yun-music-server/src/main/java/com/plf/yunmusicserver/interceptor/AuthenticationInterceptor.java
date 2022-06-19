package com.plf.yunmusicserver.interceptor;

import com.plf.yunmusicserver.annotation.LoginRequired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!isHandle(handler)){
            return true;
        }
        //获取heads进行头部的token校验
        String token = request.getHeader("token");

        request.getCookies();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean isHandle(Object handle){
        if (handle instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handle;
            LoginRequired loginRequired = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
            if (loginRequired == null){
                loginRequired = handlerMethod.getMethod().getDeclaringClass().getAnnotation(LoginRequired.class);
            }
            if (loginRequired == null){
                return false;
            }
            return true;
        }
        return true;
    }
}
