package com.HanYuYi.dao.userBase;

import com.HanYuYi.dao.BaseDao;
import com.HanYuYi.entity.UserBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取基本用户信息
 */
public class UserBaseDaoImpl implements UserBaseDao {

    /**
     * 根据单个用户名查询用户信息
     * @param connection
     * @param username
     * @return
     * @throws SQLException
     */
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

    /**
     * 修改密码
     * @param connection
     * @param username
     * @param password
     * @return
     */
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

    /**
     * 查询用户是否存在
     * @param connection
     * @param username
     * @return
     * @throws SQLException
     */
    @Override
    public boolean hasUser(Connection connection, String username) throws SQLException {
        boolean hasUser = false;
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

    /**
     * 查询用户数量
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public int userCount(Connection connection) throws SQLException {
        int backCount = 0;
        if (connection != null) {
            String sql = "SELECT COUNT(id) num FROM user_base";
            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sql);
            ResultSet resultSet = BaseDao.query(statement, null);
            if (resultSet.next()) {
                backCount = resultSet.getInt("num");
                System.out.println(backCount);
            }
            BaseDao.closeResources(null, statement, resultSet);
        }
        return backCount;
    }


    /***
     * 查询所有用户信息
     * @param connection
     * @param pageSize
     * @param pageNum
     * @return
     */
    public List<UserBase> allUserList(Connection connection, int pageSize, int pageNum) {
        List<UserBase> userList = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM user_base LIMIT ? OFFSET ?";
            Object[] sqlParams = {pageSize, pageSize * pageNum};
            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sql);
            ResultSet resultSet = BaseDao.query(statement, sqlParams);
            UserBase userBase = new UserBase();
            try {
                while (resultSet.next()) {
                    userBase.setUserName(resultSet.getString("userName"));
                    userBase.setUserCode(resultSet.getLong("userCode"));
                    userBase.setUserPassword(resultSet.getString("userPassword"));
                    userBase.setGender(resultSet.getBoolean("gender"));
                    userBase.setBirthday(resultSet.getDate("birthday"));
                    userBase.setPhone(resultSet.getString("phone"));
                    userBase.setAddress(resultSet.getString("address"));
                    userBase.setUserRole(resultSet.getLong("userRole"));
                    userBase.setCreateBy(resultSet.getLong("createBy"));
                    userBase.setCreateDate(resultSet.getDate("createDate"));
                    userBase.setModifyBy(resultSet.getLong("modifyBy"));
                    userBase.setModifyDate(resultSet.getDate("modifyDate"));
                    userList.add(userBase);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            BaseDao.closeResources(null, statement, resultSet);
        }
        return userList;
    }

    /**
     * 根据 用户名、用户角色、注册日期 分页查询用户信息
     * @return
     * @throws SQLException
     */
    public List<UserBase> userList(
            Connection connection,
            String username,
            long roleId,
            Date StartDate,
            Date endDate,
            int pageSize,
            int pageNum) throws SQLException {
        List<UserBase> userList = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT b.*, r.roleName FROM user_base b, user_role r WHERE b.userRole = r.id AND b.id = 1";
            if (username != null) {

            }
            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sql);
            ResultSet resultSet = BaseDao.query(statement, null);
            UserBase userBase = new UserBase();
            while (resultSet.next()) {

                userList.add(userBase);
            }
        }
        return userList;
    };
}
