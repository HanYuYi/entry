package com.HanYuYi.controller;

import com.HanYuYi.pojo.Blog;
import com.HanYuYi.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    @Qualifier("BlogServiceImpl")
    private BlogService blogService;

    @RequestMapping("/getBlog")
    @ResponseBody
    public String getBlogs() throws JsonProcessingException {
        List<Blog> blogs = blogService.getBlogsAll();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(simpleDateFormat);

        return objectMapper.writeValueAsString(blogs);
    }

}
