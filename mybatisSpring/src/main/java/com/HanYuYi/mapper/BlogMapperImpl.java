package com.HanYuYi.mapper;

import com.HanYuYi.pojo.Blog;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 第一种，使用注入的方式
 */
public class BlogMapperImpl implements BlogMapper {

    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Blog> getBlog(Map<Object, Object> params) {
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        return mapper.getBlog(params);
    }

    @Override
    public List<Blog> getAllBlog() {
        Blog blog = new Blog(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "话心师：46岁余秀华和90后小伙结婚，爱情的本质是有所图？",
                "极果",
                new Date(),
                920
        );
        insert(blog);
        delete("49f049f9a7ed43b68a7bae3c702e1f96");
        return sqlSession.getMapper(BlogMapper.class).getAllBlog();
    }

    @Override
    public int insert(Blog blog) {
        return sqlSession.getMapper(BlogMapper.class).insert(blog);
    }

    @Override
    public int delete(String id) {
        return sqlSession.getMapper(BlogMapper.class).delete(id);
    }
}
