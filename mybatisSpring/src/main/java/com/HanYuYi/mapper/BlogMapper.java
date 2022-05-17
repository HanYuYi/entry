package com.HanYuYi.mapper;

import com.HanYuYi.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    List<Blog> getBlog(Map<Object, Object> params);

    List<Blog> getAllBlog();

    int insert(Blog blog);

    int delete(String id);
}
