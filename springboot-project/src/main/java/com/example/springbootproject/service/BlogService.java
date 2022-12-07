package com.example.springbootproject.service;

import com.example.springbootproject.pojo.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getBlogs(String username);

}
