package com.hanyuyi.springsecuritystudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RouterController {

    @GetMapping(path = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/{level}/{pageNum}")
    public String levelPage(@PathVariable("level") String le, @PathVariable("pageNum") String num) {
        return le + "/" + num;
    }

}
