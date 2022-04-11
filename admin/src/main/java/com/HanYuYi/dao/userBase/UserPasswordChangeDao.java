package com.HanYuYi.dao.userBase;

import java.sql.SQLException;

/**
 * 根据用户名更新用户密码
 */
public interface UserPasswordChangeDao {
    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
    public boolean setUserPassword(String username, String password);

    /**
     * 查询用户是否存在
     * @param username
     * @return
     */
    public boolean hasUser(String username) throws SQLException;
}
