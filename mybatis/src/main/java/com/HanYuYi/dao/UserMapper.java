package com.HanYuYi.dao;

import com.HanYuYi.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 获取所有用户信息
     * @return
     */
    List<User> getUserInfo();

    /**
     * 查询单个用户
     * @param id
     * @return
     */
    User getUserById(long id);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUserById(long id);


    /**
     * 修改用户
     * @param columns
     * @return
     */
    int updateUser(Map<String, Object> columns);
}
