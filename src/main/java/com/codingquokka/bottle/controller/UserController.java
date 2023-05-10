package com.codingquokka.bottle.controller;


import com.codingquokka.bottle.dao.UserDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserDAO udao;


    @GetMapping("/login")
    public String login(){

        return "hi";
    }
}
