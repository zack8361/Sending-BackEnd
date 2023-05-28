package com.codingquokka.bottle.controller;

import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.service.BottleService;
import com.codingquokka.bottle.service.MailService;
import com.codingquokka.bottle.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

@Controller("/bottle")
public class BottleController {

    @Autowired
    private BottleService bottleService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AES128 aes128;

    @GetMapping("/getReceivedBottles/{encyptedEmail}")
    public ResponseEntity<String> getReceivedBottles(@PathVariable("encyptedEmail") String encyptedEmail) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("email", aes128.decrypt(encyptedEmail));

        List<Map<String, Object>> result = bottleService.getReceivedBottles(newMap);
        return ResponseEntity.ok(objectMapper.writeValueAsString(result));
    }

    @GetMapping("/getSentBottles/{encyptedEmail}")
    public ResponseEntity<String> getSentBottles(@PathVariable("encyptedEmail") String encyptedEmail) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("email", aes128.decrypt(encyptedEmail));

        List<Map<String, Object>> result = bottleService.getSentBottles(newMap);
        return ResponseEntity.ok(objectMapper.writeValueAsString(result));
    }

    @PostMapping("sendBottleLetter")
    public ResponseEntity<String> getSentBottles(@RequestBody HashMap<String, Object> map) throws JsonProcessingException {

       Map<String, Object> result = new HashMap<>();
       result.put("status", bottleService.sendBottleLetter(map));

        return ResponseEntity.ok(objectMapper.writeValueAsString(result));
    }

}
