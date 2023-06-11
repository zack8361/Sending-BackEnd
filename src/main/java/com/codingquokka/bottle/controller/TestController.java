package com.codingquokka.bottle.controller;


import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.core.MessageUtils;
import com.codingquokka.bottle.dao.UserDao;
import com.codingquokka.bottle.service.MailService;
import com.codingquokka.bottle.service.UserService;
import com.codingquokka.bottle.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private AES128 aes128;
    @GetMapping("/test")
    public ArrayList<String> testHandler() {
        ArrayList<String> list = new ArrayList<>();
        list.add("황윤규");
        list.add("이찬호");
        list.add("콩희찡꼴라");
        list.add("이하나");

        return list;
    }
    @GetMapping("/list")
    public ResponseEntity<List<String>> listTest() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("황윤규");
        list.add("이찬호");
        list.add("콩희찡꼴라");
        list.add("이하나");
        return ResponseEntity.ok(list);
    }
    @GetMapping("/map")
    public ResponseEntity<Map<String, Object>> mapTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("황윤규",26);
        map.put("이찬호",26);
        map.put("이하나",25);
        map.put("공희진",24);
        return ResponseEntity.ok(map);
    }
    @GetMapping("/mapList")
    public ResponseEntity<List<Map<String, Object>>> mapListTest() {
        List<Map<String, Object>> mapList = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "황윤규");
        map1.put("age",26);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "이찬호");
        map2.put("age",26);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "이하나");
        map3.put("age",25);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("name", "공희진");
        map4.put("age",24);

        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);

        return ResponseEntity.ok(mapList);
    }

    @GetMapping("/kkk")
    public ResponseEntity<Map<String, Object>> errorTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("code",403);
        map.put("msg","please try later");
        return ResponseEntity.status(403).body(map);
    }

//    @GetMapping("/dbTest")
//    public ResponseEntity<Map<String, Object>> dbTest() {
//        Map<String, Object> map = userDao.login();
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/transactionTest")
    public ResponseEntity transactionTest() {
        userService.insertTest();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/th/mailTest")
    public ResponseEntity mailTest() throws Exception {
        mailService.sendMail("jjss77777@naver.com", "[Bottle] 인증을 완료해주세요", "cert_Mail","");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin")
    public String adminTest() throws Exception {
        return "admin";
    }

//    @GetMapping("/propertyTest")
//    public ResponseEntity propertyTest() throws Exception {
//        System.out.println(MessageUtils.getMessage("aes.key"));
//        System.out.println(MessageUtils.getMessage("aes.iv"));
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/aesTest")
    public ResponseEntity aesTest() throws Exception {
        String str = "I love you";

        String enStr = aes128.encrypt(str, "common");
        System.out.println(enStr);
        String deStr = aes128.decrypt(enStr, "common");
        System.out.println(deStr);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/webTest")
    public ModelAndView webTest() throws Exception {
        return new ModelAndView("admin");
    }

}
