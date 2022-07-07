package com.example.springbootproject.controller;

import com.example.springbootproject.mapper.BlogMapper;
import com.example.springbootproject.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 页面控制
 */
@Controller
public class ViewController {

    @Autowired
    private BlogMapper blogMapper;

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
            // 查询日志
            List<Blog> blogsAll = blogMapper.getBlogsAll();
            model.addAttribute("blogList", blogsAll);

            return "view/blog";
        }
        model.addAttribute("loginError", "请你先登录");
        return "redirect:/";
    }

}
