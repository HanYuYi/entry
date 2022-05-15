package com.HanYuYi.dao;

import com.HanYuYi.pojo.UserByLombok;
import com.HanYuYi.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.HashMap;

class UserMapperByAnnotationMapperTest {

    @Test
    void insertUser() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession(true)) {
            UserMapperByAnnotationMapper mapper = sqlSession.getMapper(UserMapperByAnnotationMapper.class);
            HashMap<String, Object> param = new HashMap<>();
            param.put("id", 14l);
            param.put("userCode", 14l);
            param.put("userName", "bati004");
            param.put("userPassword", "123456");
            param.put("gender", true);
            param.put("birthday", new Date(693129103071L));
            param.put("phone", "13471192310");
            param.put("address", "西宁市昆仑路2号(昆仑路与黄河路交叉口西南角)");
            param.put("userRole", 2l);
            param.put("createBy", 1l);
            param.put("createDate", new Date(System.currentTimeMillis()));
            param.put("modifyBy", 1l);
            param.put("modifyDate", new Date(System.currentTimeMillis()));
            int index = mapper.insertUser(param);
            if (index > 0) {
                System.out.println("插入成功: " + index);
            }
        }
    }

    @Test
    void getUserById() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession(false)) {
            UserMapperByAnnotationMapper mapper = sqlSession.getMapper(UserMapperByAnnotationMapper.class);
            UserByLombok user = mapper.getUserById(3l);
            System.out.println(user);
        }
    }
}