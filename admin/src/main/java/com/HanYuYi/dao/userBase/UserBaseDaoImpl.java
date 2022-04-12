package com.HanYuYi.dao.userBase;

import com.HanYuYi.dao.BaseDao;
import com.HanYuYi.entity.UserBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 获取基本用户信息
 */
public class UserBaseDaoImpl implements UserBaseDao {

    @Override
    public UserBase getUserInfo(Connection connection, String username) throws SQLException {
        UserBase user = null;
        if (connection != null) {
            String sqlStr = "SELECT * FROM user_base WHERE userName = ?";
            Object[] sqlParams = {username};
            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sqlStr);
            ResultSet query = BaseDao.query(statement, sqlParams);
            if (query.next()) {
                user = new UserBase();
                user.setUserName(query.getString("userName"));
                user.setUserCode(query.getLong("userCode"));
                user.setUserPassword(query.getString("userPassword"));
                user.setGender(query.getBoolean("gender"));
                user.setBirthday(query.getDate("birthday"));
                user.setPhone(query.getString("phone"));
                user.setAddress(query.getString("address"));
                user.setUserRole(query.getLong("userRole"));
                user.setCreateBy(query.getLong("createBy"));
                user.setCreateDate(query.getDate("createDate"));
                user.setModifyBy(query.getLong("modifyBy"));
                user.setModifyDate(query.getDate("modifyDate"));
            }
            BaseDao.closeResources(null, statement, query);
        }
        return user;
    }

    @Override
    public boolean setUserPassword(Connection connection, String username, String password) {
        boolean changeStatus = false;
        if (connection != null) {
            String sql = "UPDATE user_base SET userPassword = ? WHERE userName = ?";
            Object[] sqlParams = {password, username};
            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sql);
            int updateIndex = BaseDao.update(statement, sqlParams);
            if (updateIndex > 0) {
                changeStatus = true;
            }
            BaseDao.closeResources(null, statement, null);
        }
        return changeStatus;
    }

    @Override
    public boolean hasUser(Connection connection, String username) throws SQLException {
        boolean hasUser = false;
        UserBase user = null;
        if (connection != null) {
            String sql = "SELECT userName username FROM user_base WHERE userName = ?";
            Object[] sqlParams = {username};
            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sql);
            ResultSet resultSet = BaseDao.query(statement, sqlParams);
            if (resultSet.next()) {
                hasUser = true;
            }
            BaseDao.closeResources(null, statement, resultSet);
        }
        return hasUser;
    }
}
