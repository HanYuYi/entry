package com.example.springbootproject.service;

import com.example.springbootproject.mapper.BlogMapper;
import com.example.springbootproject.pojo.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * 查询日志列表
 */
// 也可以放在启动类上
@EnableCaching
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private BlogMapper blogMapper;


    /**
     * 将查询数据放在blog区的userBlogXXX缓存中(spring的缓存)
     * @param username
     * @return
     */
    @Cacheable(value = "blog", key = "'userBlog' + #username")
    @Override
    public List<Blog> getBlogs(String username) {
        List<Blog> backList = blogMapper.getBlogsAll();
        System.out.println(backList);

        return backList;
    }


    /**
     * 清除blog区的所有缓存
     * allEntries = true
     * @return
     */
    @Override
    @CacheEvict(value = "blog", allEntries = true)
    public String clearCache() {
        return "<h1>所有缓存清除成功！</h1>";
    }


    /**
     * 清除blog区的userBlogXXX缓存
     * @return
     */
    @Override
    @CacheEvict(value = "blog", key = "'userBlog' + #username")
    public String clearCache(String username) {
        return "<h1>缓存清除成功！</h1>";
    }
}
