package com.HanYuYi.dao.userBase;

import com.HanYuYi.entity.UserBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserBaseDao {
    public UserBase getUserInfo(String username) throws SQLException;
}
