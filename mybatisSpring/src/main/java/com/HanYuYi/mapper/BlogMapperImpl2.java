package com.HanYuYi.mapper;

import com.HanYuYi.pojo.Blog;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

/**
 * 第二种，使用继承的方式
 */
public class BlogMapperImpl2 extends SqlSessionDaoSupport implements BlogMapper {
    @Override
    public List<Blog> getBlog(Map<Object, Object> params) {
        return getSqlSession().getMapper(BlogMapper.class).getBlog(params);
    }
}
