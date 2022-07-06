package com.example.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @PostMapping("/login")
    public String login() {
        return "redirect:/blog";
    }

}
