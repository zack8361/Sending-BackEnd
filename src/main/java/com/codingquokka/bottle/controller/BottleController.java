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

        List<Map<String, Object>> result = bottleService.getReceivedBottles(authMap);
        Map<String, Object> respnseData = new HashMap<>();
        respnseData.put("status", "success");
        respnseData.put("message", result);
        respnseData.put("auth", request.getAttribute("auth"));


        return ResponseEntity.ok(objectMapper.writeValueAsString(respnseData));
    }

    @GetMapping("/getSentBottles")
    public ResponseEntity<String> getSentBottles(@RequestParam HashMap<String, Object> param, HttpServletRequest request) throws Exception {
        Map<String, Object> authMap = (Map<String, Object>) request.getAttribute("authMap");

        List<Map<String, Object>> result = bottleService.getSentBottles(authMap);
        return ResponseEntity.ok(objectMapper.writeValueAsString(result));
    }

    @PostMapping("sendBottleLetter")
    public ResponseEntity<String> getSentBottles(@RequestParam HashMap<String, Object> param) throws Exception {
       Map<String, Object> result = new HashMap<>();
       result.put("status", bottleService.sendBottleLetter(param));
        return ResponseEntity.ok(objectMapper.writeValueAsString(result));
    }

}
