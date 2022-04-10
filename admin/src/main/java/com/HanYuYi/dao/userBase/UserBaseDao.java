package com.HanYuYi.dao.userBase;

import com.HanYuYi.entity.UserBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserBaseDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     * @throws SQLException
     */
    public UserBase getUserInfo(String username) throws SQLException;
}
