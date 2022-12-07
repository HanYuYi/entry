package com.example.springbootproject.service;

import com.example.springbootproject.mapper.BlogMapper;
import com.example.springbootproject.pojo.Blog;
import com.example.springbootproject.utils.DataConversion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
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
     * 将查询数据放在blog区的userBlogXXX缓存中
     * @param username
     * @return
     *
     * 清除缓存
     * @CacheEvict(value = "blog", key = "'userBlog' + username")
     */
    @Cacheable(value = "blog", key = "'userBlog' + username")
    @Override
    public List<Blog> getBlogs(String username) {

        String rBlogKey = username + ":blog";

        Jedis resource = jedisPool.getResource();

        List<String> rBlogList = resource.lrange(rBlogKey, 0, -1);

        List<Blog> backList = new ArrayList<>();

        if (rBlogList.size() > 0) {
            for (String bolg : rBlogList) {
                backList.add((Blog) DataConversion.Deserialization(bolg, new Blog()));
            }
            log.info("从redis查询的日志：");
        } else {
            backList = blogMapper.getBlogsAll();
            for (Blog blog : backList) {
                resource.lpush(rBlogKey, DataConversion. serialize(blog));
            }
            resource.expire(rBlogKey, 1 * 60 * 60);
            log.info("从mysql查询的日志：");
        }
        log.info(backList.toString());

        resource.close();

        return backList;
    }
}
