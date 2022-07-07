package com.example.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 登录控制
 */
@Controller
public class LoginController {

    /**
     * 访问登录页
     * @param session
     * @return
     */
    @GetMapping(path = {"/", "/login"})
    public String login(HttpSession session) {
        Object loginInfo = session.getAttribute("loginInfo");
        if (loginInfo == null) {
            return "login";
        }
        return "redirect:/blog";
    }

    /**
     * 处理登录请求
     * @param names
     * @param psw
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String names, @RequestParam("password") String psw, HttpSession session, Model model) {
        if (StringUtils.hasLength(names) && StringUtils.hasLength(psw)) {
            HashMap<String, Object> loginInfoMap = new HashMap<>();
            loginInfoMap.put("username", names);

            session.setAttribute("loginInfo", loginInfoMap);

            return "redirect:/blog";
        } else {
            model.addAttribute("loginError", "账号或密码错误");
            return "redirect:/";
        }
    }

}
