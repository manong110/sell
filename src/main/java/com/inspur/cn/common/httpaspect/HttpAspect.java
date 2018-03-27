package com.inspur.cn.common.httpaspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Pointcut("execution( public * com.inspur.cn.controller..*.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("请求地址 : [{}]", request.getRequestURL().toString());
        log.info("IP : [{}]" ,request.getRemoteAddr());
        log.info("方法名 : [{}]", request.getMethod());
        log.info("类方法 :[{}.{}] ", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("参数 : {}" , Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfter(Object object){
        log.info("request ->[{}]",object.toString());
    }
}
