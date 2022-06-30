package com.HanYuYi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制 thymeleaf 的 controller
 */
@RequestMapping("/page")
@Controller
public class PageController {

    @RequestMapping("/homePage")
    public String getHomePage(Model model) {
        model.addAttribute("thMe", "我是 thymeleaf 第一条信息");
        return "home";
    }
}
