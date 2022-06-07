package com.HanYuYi.mapper;

import com.HanYuYi.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMappers {

    /**
     * 插入博客
     * @param blog
     * @return
     */
    int insertBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     * @return
     */
    int deleteBlog(String id);

    /**
     * 更新
     * @param map
     * @return
     */
    int updateBlog(Map map);

    /**
     * 查询博客
     * @param map
     * @return
     */
    List<Blog> getBlogs(Map map);

    /**
     * 查询所有博客
     * @return
     */
    List<Blog> getBlogsAll();
}
