package com.codingquokka.bottle.intercepter;

import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.dao.UserDao;
import com.codingquokka.bottle.vo.UserVO;
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

    @Around("execution(* com.codingquokka.bottle.controller.BottleController.*(..))|| " +
            "execution(* com.codingquokka.bottle.controller.UserController.*(..))" +
            "execution(* com.codingquokka.bottle.controller.ChatController.*(..))" )
    public Object interceptAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserVO userVO;

        Map<String, Object> result = new HashMap<>();
        Object resultProceed = null;
        try {
            userVO = objectMapper.readValue(aes128.decrypt(request.getParameter("auth"), "login"), UserVO.class);
            now = LocalDateTime.now();
            LocalDateTime lastRequest = LocalDateTime.parse(userVO.getLastRequest(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

            if (now.isAfter(lastRequest.plusMinutes(30)) || userDao.checkUser(userVO) < 1) {
                throw new Exception();
            }

            userVO.setLastRequest(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
            request.setAttribute("auth",aes128.encrypt(objectMapper.writeValueAsString(userVO), "login"));
            request.setAttribute("authorizedUserVO", userVO);
            System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) + " - " + request.getRequestURI() + " auth : " + userVO);

        } catch (Exception e) {
            result.put("status", "unauthorized");
            result.put("message", "유효하지 않은 인증정보 입니다.");

            return ResponseEntity.ok(objectMapper.writeValueAsString(result));
        }

        try {
            resultProceed = joinPoint.proceed();
        } catch (Exception e) {
            result.put("status", "fail");
            result.put("message", "오류가 발생하였습니다.");
            System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) + " - " + request.getRequestURI() + " error : " + e.getStackTrace());
        }
        return resultProceed;
    }


}
