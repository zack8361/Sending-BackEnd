package com.codingquokka.bottle.controller;

import com.codingquokka.bottle.service.ChatContentService;
import com.codingquokka.bottle.service.ChatRoomService;
import com.codingquokka.bottle.vo.ChatContentVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatContentService chatContentService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/openChat")
    public ResponseEntity<String> openChat(@RequestParam HashMap<String, Object> param, HttpServletRequest request) throws Exception {
        Map<String, Object> authMap = (Map<String, Object>) request.getAttribute("authorizedUserVO");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("auth", request.getAttribute("auth"));

        if(!chatRoomService.isChatRoomOpen(param.get("chatId").toString())) {
            if (chatRoomService.openChatRoom(param) > 1) {
                responseData.put("status", "success");
                responseData.put("message", "채팅 생성에 성공하였습니다");
            }
            else {
                responseData.put("status", "fail");
                responseData.put("message", "채팅 생성에 실패하였습니다");
            }
        }


        return ResponseEntity.ok(objectMapper.writeValueAsString(responseData));
    }

    @GetMapping("/getChatList")
    public ResponseEntity<String> getChatList(@RequestParam HashMap<String, Object> param, HttpServletRequest request) {
        Map<String, Object> authMap = (Map<String, Object>) request.getAttribute("authorizedUserVO");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("auth", request.getAttribute("auth"));

        List<ChatContentVO> list = chatContentService.getChatContentList(param.get("chatId").toString());


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
