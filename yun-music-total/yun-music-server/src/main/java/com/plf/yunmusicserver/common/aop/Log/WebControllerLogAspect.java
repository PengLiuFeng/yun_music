package com.plf.yunmusicserver.common.aop.Log;

import com.plf.yunmusiccommon.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Slf4j
@Component
public class WebControllerLogAspect {


    @Pointcut("execution(public * com.plf.yunmusicserver.contoller..*.*(..))")
    public void controllerLogAspect(){}

    @Before("controllerLogAspect()")
    public void beforeLogAspect(JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        log.info(request.getRequestURL().toString());
        log.info(joinPoint.getSignature().getDeclaringTypeName()+":{}", JacksonUtils.javaBeanToString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "response" , pointcut = "controllerLogAspect()")
    public void afterLogAspect(Object response){
        log.info("response:{}", JacksonUtils.javaBeanToString(response));
    }

}
