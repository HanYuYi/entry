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

import java.util.List;

/**
 * 日志控制
 */
@Controller
public class BlogController {

    @Autowired
    private BlogMapper blogMapper;

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
    public List<Blog> getBlogsAll() {
        List<Blog> blogsAll = blogMapper.getBlogsAll();

        return blogsAll;
    }

}
