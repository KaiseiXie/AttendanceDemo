package com.example.attendancedemo.core.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    public String login(){
        System.out.println("jumppage");
        return "page.jsp";
    }
}
