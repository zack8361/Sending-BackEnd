package com.codingquokka.bottle.controller;


import com.codingquokka.bottle.dao.UserDao;
import com.codingquokka.bottle.service.UserService;
import com.codingquokka.bottle.core.AES128;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private UserDao udao;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AES128 aes128;

    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpSession session, @RequestBody HashMap<String, Object> map) throws Exception {
        HashMap<String, Object> param = new HashMap<>();
        param.put("email", aes128.decrypt((String) map.get("email")));
        param.put("password", map.get("password"));
        Map<String, Object> res = userService.login(param);

        Map<String, String> responseData = new HashMap<String, String>();
        if (res != null) {
            session.setAttribute("userData", res);

            responseData.put("status", "200");
            responseData.put("message", "success");
        } else {
            responseData.put("status", "500");
            responseData.put("message", "fail");
        }

        String loginResult = objectMapper.writeValueAsString(responseData); // Map을 JSON 형식으로 바꿔준다 !!
        return ResponseEntity.ok(loginResult);
    }

    @PostMapping("/join")
    public ResponseEntity<Object> join(HttpSession session, @RequestBody HashMap<String, Object> map) throws Exception {





        return null;
    }

    @GetMapping("/certUser/{encyptedUuid}")
    public ResponseEntity<Object> cert(@PathVariable("encyptedUuid") String encyptedUuid) throws Exception {

        if (userService.cert(aes128.decrypt(encyptedUuid)) == 1) {
            //인증 완료 페이지
            return null;
        }
        //인증 실패 페이지
        return null;
    }



}
