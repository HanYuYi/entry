package com.HanYuYi.dao.userBase;

import com.HanYuYi.dao.BaseDao;
import com.HanYuYi.entity.UserBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 修改密码
 */
public class UserPasswordChangeDaoImpl implements UserPasswordChangeDao {

    @Override
    public boolean setUserPassword(String username, String password) {
        boolean changeStatus = false;
        Connection connection = BaseDao.getConnection();
        if (connection != null) {
            String sql = "UPDATE user_base SET userPassword = ? WHERE userName = ?";
            Object[] sqlParams = {password, username};
            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sql);
            int updateIndex = BaseDao.update(statement, sqlParams);
            if (updateIndex > 0) {
                changeStatus = true;
            }
        }
        return changeStatus;
    }

    @Override
    public boolean hasUser(String username) throws SQLException {
        boolean hasUser = false;
        Connection connection = BaseDao.getConnection();
        UserBase user = null;
        if (connection != null) {
            String sql = "SELECT userName username FROM user_base WHERE userName = ?";
            Object[] sqlParams = {username};
            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sql);
            ResultSet resultSet = BaseDao.query(statement, sqlParams);
            if (resultSet.next()) {
                hasUser = true;
            }
            BaseDao.closeResources(connection, statement, resultSet);
        }
        return hasUser;
    }
}
