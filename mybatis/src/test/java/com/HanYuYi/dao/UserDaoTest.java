package com.HanYuYi.dao;

import com.HanYuYi.pojo.User;
import com.HanYuYi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis的使用
 */
class UserDaoTest {

    @Test
    void getUserInfo() {
        // 获取sqlSession
        /*try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            // 调用有两种方式，推荐使用1

            // 1 getMapper
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            List<User> userInfo = mapper.getUserInfo();

            // 2 select
            // List<User> userInfo1 = sqlSession.selectList("com.HanYuYi.dao.UserDao.getUserInfo");

            for (User user : userInfo) {
                System.out.println(user);
            }
        }*/

    }
}