package com.HanYuYi.mapper;

import com.HanYuYi.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    public List<Blog> getBlog(Map<Object, Object> params);
}
