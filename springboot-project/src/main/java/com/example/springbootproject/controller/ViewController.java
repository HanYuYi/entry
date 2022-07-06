package com.example.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * 页面控制
 */
@Controller
public class ViewController {

    @GetMapping(path = {"/", "/login"})
    public String login(HttpSession session) {
        Object loginInfo = session.getAttribute("loginInfo");
        if (loginInfo == null) {
            return "login";
        }
        return "redirect:/blog";
    }

    @GetMapping("/blog")
    public String toMain(HttpSession session, Model model) {
        Object loginInfo = session.getAttribute("loginInfo");
        if (loginInfo != null) {
            return "view/blog";
        }
        model.addAttribute("loginError", "请你先登录");
        return "redirect:/";
    }

}
