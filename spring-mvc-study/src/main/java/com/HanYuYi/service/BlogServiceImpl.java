package com.HanYuYi.service;

import com.HanYuYi.mapper.BlogMappers;
import com.HanYuYi.pojo.Blog;
import com.HanYuYi.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * service层 调用 dao层
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMappers blogMappers;

    public void setMapper(BlogMappers mapper) {
        this.blogMappers = mapper;
    }

    @Override
    public int insertBlog(Blog blog) {
        Blog newBlog = new Blog();
        newBlog.setId(IDUtils.getId());
        return blogMappers.insertBlog(newBlog);
    }

    @Override
    public int deleteBlog(String id) {
        return blogMappers.deleteBlog(id);
    }

    @Override
    public int updateBlog(Map map) {
        return blogMappers.updateBlog(map);
    }

    @Override
    public List<Blog> getBlogs(Map map) {
        return blogMappers.getBlogs(map);
    }

    @Override
    public List<Blog> getBlogsAll() {
        return blogMappers.getBlogsAll();
    }
}
