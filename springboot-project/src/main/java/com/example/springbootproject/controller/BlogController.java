package com.example.springbootproject.controller;

import com.example.springbootproject.pojo.Blog;
import com.example.springbootproject.service.BlogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 日志控制
 */
@Slf4j
@Controller
public class BlogController {

    @Autowired
    private BlogServiceImpl blogServiceImpl;


    /**
     * 访问日志页
     * @param model
     * @return
     */
    @GetMapping("/blog")
    public String toMain(HttpSession session, Model model) {


        // 查询日志
        List<Blog> blogList = (List<Blog>) model.getAttribute("blogList");
        if (blogList == null) {
            HashMap<String, Object> loginInfoMap = (HashMap<String, Object>) session.getAttribute("loginInfo");
            String username = (String) loginInfoMap.get("username");

            model.addAttribute("blogList", blogServiceImpl.getBlogs(username));
        }

        return "view/blog";
    }

    /**
     * 查询所有日志，响应纯数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBlogsAll")
    public List<Blog> getBlogsAll(HttpSession session) {
        HashMap<String, Object> loginInfoMap = (HashMap<String, Object>) session.getAttribute("loginInfo");
        String username = (String) loginInfoMap.get("username");

        return blogServiceImpl.getBlogs(username);
    }


    /**
     * 清除blog的缓存
     * @return
     */
    @ResponseBody
    @RequestMapping("/clearCache")
    public String clearCache(String all, HttpSession session) {
        if (all != null && all.equals("1")) {
            return blogServiceImpl.clearCache();
        }

        HashMap<String, Object> loginInfoMap = (HashMap<String, Object>) session.getAttribute("loginInfo");
        String username = (String) loginInfoMap.get("username");

        return blogServiceImpl.clearCache(username);
    }
}
