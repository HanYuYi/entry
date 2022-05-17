package com.HanYuYi.mapper;

import com.HanYuYi.pojo.Blog;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BlogMapperTest {

    /**
     * 测试 spring、mybatis 整合
     * @throws IOException
     */
    @Test
    public void getBlog() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlogMapper blogMapper = context.getBean("blogMapper", BlogMapper.class);
        BlogMapper blogMapper2 = context.getBean("blogMapper2", BlogMapper.class);

        int[] viewsSection = {1000, 10000};
        HashMap<Object, Object> params = new HashMap<>();
        params.put("viewsSection", viewsSection);


//        List<Blog> blog = blogMapper.getBlog(params);
        List<Blog> blog2 = blogMapper2.getBlog(params);

        System.out.println(blog2);
    }

    /**
     * 测试事务
     */
    @Test
    public void transactions() {
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlogMapper blogMapper = context.getBean("blogMapper", BlogMapper.class);

        for (Blog blog : blogMapper.getAllBlog()) {
            System.out.println(blog);
        }

    }
}