package com.HanYuYi.dao;

import com.HanYuYi.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 获取用户信息
     * @return
     */
    List<User> getUserInfo();
}
