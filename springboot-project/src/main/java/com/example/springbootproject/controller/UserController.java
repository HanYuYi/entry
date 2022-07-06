package com.example.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 登录控制
 */
@Controller
public class UserController {

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, Model model) {
        if (StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
            HashMap<String, Object> loginInfoMap = new HashMap<>();
            loginInfoMap.put("username", username);

            session.setAttribute("loginInfo", loginInfoMap);

            return "redirect:/blog";
        } else {
            model.addAttribute("loginError", "账号或密码错误");
            return "redirect:/";
        }
    }

}
