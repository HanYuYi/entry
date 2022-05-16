package com.HanYuYi.mapper;

import com.HanYuYi.pojo.Blog;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BlogMapperTest {

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
}