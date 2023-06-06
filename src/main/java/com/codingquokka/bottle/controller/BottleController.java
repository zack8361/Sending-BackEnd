package com.codingquokka.bottle.controller;

import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.service.BottleService;
import com.codingquokka.bottle.service.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/bottle")
public class BottleController {

    @Autowired
    private BottleService bottleService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AES128 aes128;

    @GetMapping("/getReceivedBottles")
    public ResponseEntity<String> getReceivedBottles(@RequestParam HashMap<String, Object> param, HttpServletRequest request) throws Exception {
        Map<String, Object> authMap = (Map<String, Object>) request.getAttribute("authMap");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("auth", request.getAttribute("auth"));

        List<Map<String, Object>> result = bottleService.getReceivedBottles(authMap);

        if (result != null) {
            responseData.put("status", "success");
            responseData.put("message", objectMapper.writeValueAsString(result));
        }
        else {
            responseData.put("status", "fail");
            responseData.put("message", "보낸 메세지 로드에 실패하였습니다.\n관리자에게 문의하세요");
        }

        return ResponseEntity.ok(objectMapper.writeValueAsString(responseData));
    }

    @GetMapping("/getSentBottles")
    public ResponseEntity<String> getSentBottles(@RequestParam HashMap<String, Object> param, HttpServletRequest request) throws Exception {
        Map<String, Object> authMap = (Map<String, Object>) request.getAttribute("authMap");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("auth", request.getAttribute("auth"));

        List<Map<String, Object>> result = bottleService.getSentBottles(authMap);

        if (result != null) {
            responseData.put("status", "success");
            responseData.put("message", objectMapper.writeValueAsString(result));
        }
        else {
            responseData.put("status", "fail");
            responseData.put("message", "보낸 메세지 로드에 실패하였습니다.\n관리자에게 문의하세요");
        }

        return ResponseEntity.ok(objectMapper.writeValueAsString(responseData));
    }

    @PostMapping("sendBottleLetter")
    public ResponseEntity<String> sendBottleLetter(@RequestParam HashMap<String, Object> param, HttpServletRequest request) throws Exception {
        Map<String, Object> authMap = (Map<String, Object>) request.getAttribute("authMap");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("auth", request.getAttribute("auth"));

        param.put("sender_id", authMap.get("EMAIL"));
        int result = bottleService.sendBottleLetter(param);

        if (result == 1) {
           responseData.put("status", "success");
           responseData.put("message", "메세지 전송에 성공하였습니다.");
       }
       else {
           responseData.put("status", "fail");
           responseData.put("message", "메세지 전송에 실패하였습니다.\n관리자에게 문의하세요");

       }
        return ResponseEntity.ok(objectMapper.writeValueAsString(responseData));
    }

}
