package com.HanYuYi.dao;

import com.HanYuYi.pojo.Blog;
import com.HanYuYi.utils.IDUtils;
import com.HanYuYi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogMapperTest {
    @Test
    void insertBlog() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession(true)) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

            Blog blog = new Blog();
            blog.setId(IDUtils.getId());
            blog.setTitle("古天乐：青春的时光，其实只占人生的一小部分");
            blog.setAuthor("极果");
            blog.setCreateTime(new Date());
            blog.setViews(996);

            mapper.insertBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("高娓娓：一封美国神秘房东的来信——我想分你点钱");
            blog.setViews(1382);

            mapper.insertBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("陈晓《好好说话》，告诉我们“说话不难，说好不易”");
            blog.setViews(9210);

            mapper.insertBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("韩浩月：黄蜀芹和她的作品，记录着一个时代");
            blog.setViews(1003);

            mapper.insertBlog(blog);
        }
    }

    @Test
    void getBlogListByIf() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession(false)) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

            Map<String, Object> params = new HashMap<>();
            params.put("author", "极果");
            params.put("views", "1000");
            List<Blog> blogList = mapper.getBlogListByIf(params);
            System.out.println(blogList);
        }
    }

    @Test
    void getBlogListByChoose() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession(false)) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

            Map<String, Object> params = new HashMap<>();
            params.put("title", "韩浩月：黄蜀芹和她的作品，记录着一个时代");
            params.put("author", "极果");
            params.put("views", "1000");
            List<Blog> blogList = mapper.getBlogListByChoose(params);
            System.out.println(blogList);
        }
    }

    @Test
    void updateBySet() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession(true)) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

            Map<String, Object> params = new HashMap<>();
            params.put("id", "0c8bac05d4484d45a925c21bf0a436bf");
            params.put("title", "温馨雅兰：保时捷运动驾校，锲而不舍的邀请我执教");
            params.put("author", "极果");
            params.put("views", "13090");
            int index = mapper.updateBySet(params);
            System.out.println(index);
        }
    }
}
