package com.HanYuYi.dao;

import com.HanYuYi.pojo.User;
import com.HanYuYi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.sql.Date;
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
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            // 调用有两种方式，推荐使用1

            // 1 getMapper
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userInfo = mapper.getUserInfo();

            // 2 select
            // List<User> userInfo1 = sqlSession.selectList("com.HanYuYi.dao.UserDao.getUserInfo");

            for (User user : userInfo) {
                System.out.println(user);
            }
        }
    }

    @Test
    void getUserById(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User userInfo = mapper.getUserById(17L);
            System.out.println(userInfo);
        }
    }

    @Test
    void insertUser() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int rowIndex = mapper.insertUser(new User(17L, 17L, "bati001", "123456", true, new Date(693792003400L), "13755412097", "安庆市宜秀区振风大道与独秀大道交叉口东北200米", 2L, 1L, new Date(System.currentTimeMillis()), 1L, new Date(System.currentTimeMillis())));
            System.out.println(rowIndex);
            // 执行事务
            sqlSession.commit();
        }
    }

    @Test
    void deleteUserById() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int rowIndex = mapper.deleteUserById(14L);
            System.out.println(rowIndex);
            sqlSession.commit();
        }
    }

    @Test
    void updateUser() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
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
}