package com.codingquokka.bottle.controller;

import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.service.BottleService;
import com.codingquokka.bottle.service.MailService;
import com.codingquokka.bottle.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

    @PostMapping("/getReceivedBottles")
    public ResponseEntity<String> getReceivedBottles(@RequestParam HashMap<String, Object> map
    ) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, JsonProcessingException {

        System.out.println("asdasdasdasdasdas");
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("email", aes128.decrypt(map.get("email").toString(), "common"));
        List<Map<String, Object>> result = bottleService.getReceivedBottles(newMap);

        Map<String, Object> respnseData = new HashMap<>();
        respnseData.put("status", "success");
        respnseData.put("message", result);


        return ResponseEntity.ok(objectMapper.writeValueAsString(respnseData));
    }

    @GetMapping("/getSentBottles/{encyptedEmail}")
    public ResponseEntity<String> getSentBottles(@PathVariable("encryptedEmail") String encyptedEmail) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("email", aes128.decrypt(encyptedEmail, "common"));
        List<Map<String, Object>> result = bottleService.getSentBottles(newMap);
        return ResponseEntity.ok(objectMapper.writeValueAsString(result));
    }

    @PostMapping("sendBottleLetter")
    public ResponseEntity<String> getSentBottles(@RequestParam HashMap<String, Object> map) throws JsonProcessingException {
       Map<String, Object> result = new HashMap<>();
       result.put("status", bottleService.sendBottleLetter(map));
        return ResponseEntity.ok(objectMapper.writeValueAsString(result));
    }

}
