package com.HanYuYi.dao;

import com.HanYuYi.pojo.RoleByLombok;
import com.HanYuYi.pojo.User;
import com.HanYuYi.pojo.UserByLombok;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 获取所有用户信息
     * @return
     */
    List<User> getUserInfo(Map<String, Integer> params);

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

    /**
     * 一对多查询用户(关联查询)
     * @return
     */
    List<Map<String, Object>> getUserOneToManyBySelect();

    /**
     * 一对多查询用户(子查询)
     * @return
     */
    List<Map<String, Object>> getUserOneToManyByChild();

}
