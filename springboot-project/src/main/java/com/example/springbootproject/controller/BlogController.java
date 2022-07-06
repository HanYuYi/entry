package com.example.springbootproject.controller;

import com.example.springbootproject.mapper.BlogMapper;
import com.example.springbootproject.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @ResponseBody
    @RequestMapping("/getBlogsAll")
    public List<Blog> getBlogsAll() {
        List<Blog> blogsAll = blogMapper.getBlogsAll();

        return blogsAll;
    }

}
