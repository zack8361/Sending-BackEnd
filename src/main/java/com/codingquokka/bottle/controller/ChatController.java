package com.codingquokka.bottle.controller;

import com.codingquokka.bottle.service.ChatService;
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
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;


    @PostMapping("/openChat")
    public ResponseEntity<String> openChat(@RequestParam HashMap<String, Object> param, HttpServletRequest request) {
        Map<String, Object> authMap = (Map<String, Object>) request.getAttribute("authorizedUserVO");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("auth", request.getAttribute("auth"));


        return ResponseEntity.ok("");
    }

    @GetMapping("/getChatList")
    public ResponseEntity<String> getChatList(@RequestParam HashMap<String, Object> param, HttpServletRequest request) {
        Map<String, Object> authMap = (Map<String, Object>) request.getAttribute("authorizedUserVO");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("auth", request.getAttribute("auth"));

        return ResponseEntity.ok("");
    }

    @PostMapping("/sendChatMsg")
    public ResponseEntity<String> sendChatMsg(@RequestParam HashMap<String, Object> param, HttpServletRequest request) {
        Map<String, Object> authMap = (Map<String, Object>) request.getAttribute("authorizedUserVO");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("auth", request.getAttribute("auth"));

        return ResponseEntity.ok("");
    }



}
