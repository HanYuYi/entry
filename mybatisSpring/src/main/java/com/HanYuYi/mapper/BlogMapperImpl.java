package com.HanYuYi.mapper;

import com.HanYuYi.pojo.Blog;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

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
}
