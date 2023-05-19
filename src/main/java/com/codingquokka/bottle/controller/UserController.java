package com.codingquokka.bottle.controller;


import com.codingquokka.bottle.dao.UserDao;
import com.codingquokka.bottle.service.UserService;
import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AES128 aes128;

    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpSession session, @RequestBody HashMap<String, Object> map) throws Exception {
        map.put("email", aes128.decrypt((String) map.get("email")));
        Map<String, Object> res = userService.login(map);

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
    public ResponseEntity<Object> join(@RequestBody HashMap<String, Object> map) throws Exception  {
        map.put("email",aes128.decrypt(map.get("email").toString()));
        map.put("uuid",UUID.randomUUID().toString());

        Map<String, String> responseData = new HashMap<String, String>();
        try{
            if (userService.join(map) == 1) {
                responseData.put("status", "200");
                responseData.put("message", "success");
            }
        } catch(Exception e) {
            responseData.put("status", "500");
            responseData.put("message", "fail");

        }
        String joinResult = objectMapper.writeValueAsString(responseData); // Map을 JSON 형식으로 바꿔준다 !!
        return ResponseEntity.ok(joinResult);
    }

    @PostMapping("/checkEmail")
    public ResponseEntity<Object> checkEmail(@RequestBody HashMap<String, Object> map) throws Exception {
        map.put("email", aes128.decrypt((String) map.get("email")));

        int res = userService.checkEmail(map);

        Map<String, String> responseData = new HashMap<String, String>();
        if(res == 1){
            responseData.put("status", "500");
            responseData.put("message", "fail");
        } else {
            responseData.put("status", "200");
            responseData.put("message", "success");
        }
        String checkEmailResult = objectMapper.writeValueAsString(responseData); // Map을 JSON 형식으로 바꿔준다 !!
        return ResponseEntity.ok(checkEmailResult);
    }

    @GetMapping("/certUser/{encyptedUuid}")
    public ModelAndView cert(@PathVariable("encyptedUuid") String encyptedUuid) throws Exception {

        ModelAndView mv = new ModelAndView();

        if (userService.cert(aes128.decrypt(encyptedUuid)) == 1) {
            mv.setViewName("/cert/cert_Success");
        }
        else {
            mv.setViewName("/cert/cert_Fail");

        }
        return mv;
    }



}
