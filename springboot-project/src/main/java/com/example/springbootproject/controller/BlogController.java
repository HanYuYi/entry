package com.example.springbootproject.controller;

import com.example.springbootproject.mapper.BlogMapper;
import com.example.springbootproject.pojo.Blog;
import com.example.springbootproject.utils.DataConversion;
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

        HashMap<String, Object> loginInfoMap = (HashMap<String, Object>) session.getAttribute("loginInfo");
        Object username = loginInfoMap.get("username");

        List<Blog> blogsAll = new ArrayList<>();

        String rBlogKey = username + ":blog";

        List<String> rBlogAll = resource.lrange(rBlogKey, 0, -1);

        if (rBlogAll.size() > 0) {
            for (String bolg : rBlogAll) {
                blogsAll.add((Blog) DataConversion.Deserialization(bolg, new Blog()));
            }
            log.info("从redis查询的日志：");
        } else {
            blogsAll = blogMapper.getBlogsAll();
            for (Blog blog : blogsAll) {
                resource.lpush(rBlogKey, DataConversion. serialize(blog));
            }
            log.info("从mysql查询的日志：");
        }
        log.info(blogsAll.toString());

        resource.close();

        return blogsAll;
    }

}
