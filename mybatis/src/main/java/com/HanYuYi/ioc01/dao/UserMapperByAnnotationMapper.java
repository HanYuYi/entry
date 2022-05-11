package com.HanYuYi.ioc01.dao;

import com.HanYuYi.pojo.UserByLombok;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 使用注解查询，可用于简单的sql查询
 */
public interface UserMapperByAnnotationMapper {

    @Insert("INSERT INTO user_base (id,userCode,userName,userPassword,gender,birthday,phone,address,userRole,createBy,createDate,modifyBy,modifyDate) VALUES (#{id},#{userCode},#{userName},#{userPassword},#{gender},#{birthday},#{phone},#{address},#{userRole},#{createBy},#{createDate},#{modifyBy},#{modifyDate})")
    int insertUser(Map<String, Object> user);

    @Select("SELECT * FROM user_base WHERE id = #{uid}")
    UserByLombok getUserById(@Param("uid") long id); // 使用注解sql的时候，String 和基本类型的参数大于两个时， 必须使用@Param
}
