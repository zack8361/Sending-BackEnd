package com.codingquokka.bottle.controller;


import com.codingquokka.bottle.service.*;
import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
public class CommonController {

    @Autowired
    private FirebaseCloudMessageService firebaseCloudMessageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AES128 aes128;

    @Autowired
    private EmoticonService emoticonService;

    @Autowired
    private FcmTokenService fcmTokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam HashMap<String, Object> params) throws Exception {

        System.out.println("map = " + params);
        params.put("email", aes128.decrypt((String) params.get("email"), "common"));
        UserVO loginUser = userService.login(params);
        fcmTokenService.insertToken(params);


        Map<String, String> responseData = new HashMap<String, String>();
        if (loginUser != null) {
            if (loginUser.getCertified() == 1) {
                loginUser.setLastRequest(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
                responseData.put("auth", aes128.encrypt(objectMapper.writeValueAsString(loginUser), "login"));
                responseData.put("status", "success");
                responseData.put("message", "성공");
            } else {
                responseData.put("status", "fail");
                responseData.put("message", "인증되지 않은 계정입니다.");
            }
        } else {
            responseData.put("status", "fail");
            responseData.put("message", "존재하지 않는 계정정보입니다.\n이메일과 비밀번호를 확인해주세요.");
        }
        String loginResult = objectMapper.writeValueAsString(responseData); // Map을 JSON 형식으로 바꿔준다 !!
        return ResponseEntity.ok(loginResult);
    }

    @PostMapping("/join")
    public ResponseEntity<Object> join(@RequestParam HashMap<String, Object> map) throws Exception {

        map.put("email", aes128.decrypt(map.get("email").toString(), "common"));
        map.put("uuid", UUID.randomUUID().toString());
        Map<String, String> responseData = new HashMap<String, String>();
        String[] email = map.get("email").toString().split("@");
        map.put("belong", email[1]);
        map.put("domain_cd", email[1]);
//      회원 가입 로직
        int result = userService.join(map);


//      회원 가입 성공시 토큰 -> fcm table 에 insert
        if (result == 1) {
            fcmTokenService.insertToken(map);
            responseData.put("status", "success");
            responseData.put("message", "회원가입을 위한 인증 메일이 전송되었습니다.");
        } else if (result == -1) {
            responseData.put("status", "fail");
            responseData.put("message", "가입할 수 없는 메일 도메인입니다.\n(회사, 조직, 학교메일이 아닌 공용 메일 도메인)");
        }
        String joinResult = objectMapper.writeValueAsString(responseData); // Map을 JSON 형식으로 바꿔준다 !!

        return ResponseEntity.ok(joinResult);
    }

    @PostMapping("/checkEmail")
    public ResponseEntity<Object> checkEmail(@RequestParam HashMap<String, Object> map) throws Exception {
        map.put("email", aes128.decrypt((String) map.get("email"), "common"));

        int res = userService.checkEmail(map);

        Map<String, String> responseData = new HashMap<String, String>();
        if (res == 1) {
            responseData.put("status", "fail");
            responseData.put("message", "이미 존재하는 계정입니다.");
        } else if (res == -1) {
            responseData.put("status", "fail");
            responseData.put("message", "가입할 수 없는 메일 도메인입니다.\n(회사, 조직, 학교메일이 아닌 공용 메일 도메인)");
        } else {
            responseData.put("status", "success");
            responseData.put("message", "가입 가능한 계정입니다.");
        }
        String checkEmailResult = objectMapper.writeValueAsString(responseData); // Map을 JSON 형식으로 바꿔준다 !!
        return ResponseEntity.ok(checkEmailResult);
    }

    @GetMapping("/certUser")
    public String cert(@RequestParam("data") String data) throws Exception {

        if (userService.cert(aes128.decrypt(data, "common")) == 1) {
            return "cert_Success";
        } else {
            return "cert_Fail";
        }

    }
    @GetMapping("/getEmoticon/{emoId}")
    public ResponseEntity<Object> getEmoticon(@PathVariable("emoId") int emoId) {
        Map<String, String> responseData = new HashMap<>();

        String emoticon = emoticonService.getEmoticon(emoId);

        if (emoticon != null) {
            responseData.put("status", "success");
            responseData.put("message", emoticon);
        } else {
            responseData.put("status", "fail");
        }
        return ResponseEntity.ok(responseData);
    }


}
