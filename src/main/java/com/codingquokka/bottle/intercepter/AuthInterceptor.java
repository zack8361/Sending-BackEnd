package com.codingquokka.bottle.intercepter;

import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.dao.UserDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AuthInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AES128 aes128;

    private LocalDateTime now = null;

    @Around("execution(* com.codingquokka.bottle.controller.BottleController.*(..))")
    public Object interceptAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> authMap;
        try {
            authMap = objectMapper.readValue(aes128.decrypt(request.getParameter("auth"), "login"), Map.class);
            now = LocalDateTime.now();
            LocalDateTime lastRequest = LocalDateTime.parse(authMap.get("LAST_REQUEST").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

            if (now.isAfter(lastRequest.plusMinutes(30)) || userDao.checkUser(authMap) < 1) {
                throw new Exception();
            }

            authMap.put("LAST_REQUEST", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
            request.setAttribute("auth",aes128.encrypt(objectMapper.writeValueAsString(authMap), "login"));
            request.setAttribute("authMap", authMap);

        } catch (Exception e) {
            Map<String, Object> result = new HashMap<>();
            result.put("status", "fail");
            result.put("message", "유효하지 않은 인증정보 입니다.");

            return ResponseEntity.ok(objectMapper.writeValueAsString(result));
        }
        Object result = joinPoint.proceed();

        return result;
    }


}
