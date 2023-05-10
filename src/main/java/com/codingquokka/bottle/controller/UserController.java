package com.codingquokka.bottle.controller;


import com.codingquokka.bottle.dao.UserDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserDao udao;


    @GetMapping("/login")
    public String login(){

        return "hi";
    }
}
