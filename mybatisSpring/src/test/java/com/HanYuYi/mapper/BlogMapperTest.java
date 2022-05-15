package com.HanYuYi.mapper;

import com.HanYuYi.pojo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class BlogMapperTest {

    @Test
    public void getBlog() throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        int[] viewsSection = {1000, 10000};
        HashMap<Object, Object> params = new HashMap<>();
        params.put("viewsSection", viewsSection);
        List<Blog> blog = mapper.getBlog(params);
        System.out.println(blog);
    }
}