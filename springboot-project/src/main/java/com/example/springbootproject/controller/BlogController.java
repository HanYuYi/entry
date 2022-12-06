package com.example.springbootproject.controller;

import com.example.springbootproject.mapper.BlogMapper;
import com.example.springbootproject.pojo.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 日志控制
 */
@Slf4j
@Controller
public class BlogController {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private JedisPool jedisPool;


    /**
     * 访问日志页
     * @param model
     * @return
     */
    @GetMapping("/blog")
    public String toMain(Model model) {



        // 查询日志
        List<Blog> blogsAll = blogMapper.getBlogsAll();
        model.addAttribute("blogList", blogsAll);

        return "view/blog";
    }

    /**
     * 查询所有日志，响应纯数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBlogsAll")
    public List<Blog> getBlogsAll(HttpSession session) {
        Jedis resource = jedisPool.getResource();

        log.info("查询redis getBlogsAll 结果：");
        HashMap<String, Object> loginInfoMap = (HashMap<String, Object>) session.getAttribute("loginInfo");
        Object username = loginInfoMap.get("username");

        List<Blog> blogsAll = new ArrayList<>();

        List<String> rBlogAll = resource.lrange(username + "blog", 0, -1);

        if (rBlogAll.size() > 0) {
            for (String bolg : rBlogAll) {

            }
        } else {
            blogsAll = blogMapper.getBlogsAll();
        }
        System.out.println(rBlogAll);

        resource.close();


        return blogsAll;
    }

}
