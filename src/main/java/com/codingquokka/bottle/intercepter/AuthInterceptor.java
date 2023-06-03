package com.codingquokka.bottle.intercepter;

import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.dao.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class AuthInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AES128 aes128;

    @Around("execution(* com.codingquokka.bottle.controller.BottleController.*(..))")
    public Object interceptAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();



        Object result = joinPoint.proceed();


        return result;
    }


}
