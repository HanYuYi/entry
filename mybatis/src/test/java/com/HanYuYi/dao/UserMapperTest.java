package com.HanYuYi.dao;

import com.HanYuYi.pojo.User;
import com.HanYuYi.pojo.UserByLombok;
import com.HanYuYi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.*;

/**
 * mybatis的使用
 */
class UserMapperTest {

    // log4j对象
    static Logger logger = Logger.getLogger(UserMapperTest.class);

    @Test
    void getUserInfo() {
        // 获取sqlSession
        try(SqlSession sqlSession = MybatisUtils.getSqlSession(false)) {
            // 调用有两种方式，推荐使用1

            // 1 getMapper
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            HashMap<String, Integer> params = new HashMap<>();
            params.put("index", 0);
            params.put("size", 5);
            List<User> userInfo = mapper.getUserInfo(params);

            // 2 select(不推荐使用)
            // List<User> userInfo1 = sqlSession.selectList("com.HanYuYi.dao.UserDao.getUserInfo");

            for (User user : userInfo) {
                System.out.println(user);
            }
            // log4j使用
            logger.info("log4j:info 这是一条info");
            logger.debug("log4j:debug 这是一条debug");
            logger.error("log4j:error 这是一条error");
        }
    }

    @Test
    void getUserById(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession(false)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User userInfo = mapper.getUserById(11L);
            System.out.println(userInfo);
        }
    }

    @Test
    void insertUser() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession(false)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int rowIndex = mapper.insertUser(new User(18L, 18L, "bati002", "123456", false, new Date(693122003071L), "17988541710", "金朱东路与金阳北路交叉口5号(烈变国际广场旁)", 2L, 1L, new Date(System.currentTimeMillis()), 1L, new Date(System.currentTimeMillis())));
            System.out.println(rowIndex);
            // 执行事务
            sqlSession.commit();
        }
    }

    @Test
    void deleteUserById() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession(false)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int rowIndex = mapper.deleteUserById(14L);
            System.out.println(rowIndex);
            sqlSession.commit();
        }
    }

    @Test
    void updateUser() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession(false)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            Map<String, Object> columns = new HashMap<>();
            columns.put("id", 17L);
            columns.put("phone", "18111278471");
            columns.put("birthday", new Date(693794803410L));

            int rowIndex = mapper.updateUser(columns);
            System.out.println(rowIndex);
            sqlSession.commit();
        }
    }

    /**
     * 第一种对多一查询
     */
    @Test
    void getUserOneToManyBySelect() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession(false)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<Map<String, Object>> userOneToManyList = mapper.getUserOneToManyBySelect();
            for (Map<String, Object> userByLombok : userOneToManyList) {
                System.out.println(userByLombok);
            }
        }
    }

    /**
     * 第二种对多一查询
     */
    @Test
    void getUserOneToManyByChild() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession(false)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<Map<String, Object>> userOneToManyList = mapper.getUserOneToManyByChild();
            for (Map<String, Object> userByLombok : userOneToManyList) {
                System.out.println(userByLombok);
            }
        }
    }

}