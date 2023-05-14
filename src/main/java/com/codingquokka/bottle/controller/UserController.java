package com.codingquokka.bottle.controller;


import com.codingquokka.bottle.dao.UserDao;
import com.codingquokka.bottle.service.UserService;
import com.codingquokka.bottle.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private UserDao udao;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpSession session, @RequestBody HashMap<String,Object> map) throws Exception{

        System.out.println(map);
        System.out.println(map.get("email"));
        Map<String, Object> res = userService.login(map);

        if(res != null){
            session.setAttribute("login",res);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(200).build();
    }
}
