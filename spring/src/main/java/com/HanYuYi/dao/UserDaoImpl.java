package com.HanYuYi.dao;

public class UserDaoImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("你好！dao获取默认用户！");
    }
}
