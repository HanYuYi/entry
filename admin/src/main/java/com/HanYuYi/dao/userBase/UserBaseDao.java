package com.HanYuYi.dao.userBase;

import com.HanYuYi.entity.UserBase;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserBaseDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     * @throws SQLException
     */
    public UserBase getUserInfo(Connection connection, String username) throws SQLException;

    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
    public boolean setUserPassword(Connection connection, String username, String password);

    /**
     * 查询用户是否存在
     * @param username
     * @return
     */
    public boolean hasUser(Connection connection, String username) throws SQLException;
}
