package com.codingquokka.bottle.controller;

import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.service.EmoticonService;
import com.codingquokka.bottle.service.MailDomainService;
import com.codingquokka.bottle.service.MailService;
import com.codingquokka.bottle.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailDomainService mailDomainService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AES128 aes128;

    @Autowired
    private MailService mailService;

    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@RequestParam HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        Map<String, Object> authMap = (Map<String, Object>) request.getAttribute("authMap");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("auth", request.getAttribute("auth"));

        params.put("EMAIL", authMap.get("EMAIL"));
        params.put("UUID", authMap.get("UUID"));
        if (userService.changePassword(params) > 0) {
            responseData.put("status", "success");
            responseData.put("message", "비밀번호 변경에 성공하였습니다.\n안정적 서비스 이용을 위해 다시 로그인해 주세요");
        } else {
            responseData.put("status", "fail");
            responseData.put("message", "비밀번호 변경에 실패하였습니다.\n관리자에게 문의하세요");
        }

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<String> getUserInfo(@RequestParam HashMap<String, Object> params, HttpServletRequest request) throws Exception {

        Map<String, Object> authMap = (Map<String, Object>) request.getAttribute("authMap");
        for (String s : authMap.keySet()) {
            System.out.println("s + \" = \" + authMap.get(s) = " + s + " = " + authMap.get(s));
        }

//        윤규야 여기 수정좀 할게. return type 이 없어서 select 부문 에러났음.
        Map<String,Object> result = userService.getUserInfo(authMap);

        Map<String, Object> responseData = new HashMap<>();
        System.out.println("authMap = " + authMap);
        responseData.put("auth", request.getAttribute("auth"));
        responseData.put("status", "success");
        responseData.put("message",result);

        return ResponseEntity.ok(objectMapper.writeValueAsString(responseData));
    }

}
