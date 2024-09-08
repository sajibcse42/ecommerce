package com.example.EcommerceApplication.controller;

import com.example.EcommerceApplication.entity.User;
import com.example.EcommerceApplication.utils.SessionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class HelloController {

    @GetMapping("hello")
    public String helloWorld(){
        User userInfo= SessionManager.getLoggedInUserDetailsInfo();
        return "Hello";
    }

}

