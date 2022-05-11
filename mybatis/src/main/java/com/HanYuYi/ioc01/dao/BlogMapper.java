package com.HanYuYi.ioc01.dao;

import com.HanYuYi.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * mybatis 动态sql
 */
public interface BlogMapper {

    /**
     * 插入博客
     * @param blog
     * @return
     */
    int insertBlog(Blog blog);

    /**
     * if 查询
     * @param map
     * @return
     */
    List<Blog> getBlogListByIf(Map map);

    /**
     * Choose 查询
     * @param map
     * @return
     */
    List<Blog> getBlogListByChoose(Map map);

    /**
     * set 更新
     * @param map
     * @return
     */
    int updateBySet(Map map);

    List<Blog> queryByEach(Map map);
}
