package com.codingquokka.bottle.controller;

import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.service.MailDomainService;
import com.codingquokka.bottle.service.MailService;
import com.codingquokka.bottle.service.UserService;
import com.codingquokka.bottle.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
        UserVO authorizedUserVO = (UserVO) request.getAttribute("authorizedUserVO");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("auth", request.getAttribute("auth"));

        params.put("EMAIL", authorizedUserVO.getEmail());
        params.put("UUID", authorizedUserVO.getUuid());
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
        UserVO authorizedUserVO = (UserVO) request.getAttribute("authorizedUserVO");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("auth", request.getAttribute("auth"));
        responseData.put("status", "success");
        responseData.put("message", objectMapper.writeValueAsString(userService.getUserInfo(authorizedUserVO.getEmail())));

        return ResponseEntity.ok(objectMapper.writeValueAsString(responseData));
    }

}
