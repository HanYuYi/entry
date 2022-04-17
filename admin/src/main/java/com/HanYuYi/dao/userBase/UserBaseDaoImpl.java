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
     * 根据 用户名、用户角色、注册日期查询用户数量
     * @param connection
     * @param username
     * @param roleId
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException
     */
    @Override
    public int userCount(Connection connection, String username, long roleId, String startDate, String endDate) throws SQLException {
        int backCount = 0;
        if (connection != null) {
            // sql拼接
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT COUNT(1) num FROM user_base b, user_role r WHERE b.userRole = r.id");

            List<Object> paramsList = new ArrayList<>();

            if (username != null) {
                paramsList.add("%"+username+"%");
                sql.append(" AND b.userName LIKE ?");
            }
            if (roleId != 0) {
                paramsList.add(roleId);
                sql.append(" AND b.userRole = ?");
            }
            if (startDate != null && endDate != null) {
                paramsList.add(startDate);
                paramsList.add(endDate);
                sql.append(" AND b.createDate >= ? AND b.createDate <= ?");
            }

            Object[] paramsArr = paramsList.toArray();

            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sql.toString());
            ResultSet resultSet = BaseDao.query(statement, paramsArr);
            if (resultSet.next()) {
                backCount = resultSet.getInt("num");
            }
            BaseDao.closeResources(null, statement, resultSet);
        }
        return backCount;
    }

    /**
     * 根据 用户名、用户角色、注册日期 分页查询用户信息
     * @return
     * @throws SQLException
     */
    public List<UserBase> userList(Connection connection, String username, long roleId, String startDate, String endDate, int pageSize, int pageNum) throws SQLException {
        List<UserBase> userList = new ArrayList<>();
        if (connection != null) {
            StringBuilder sql = new StringBuilder("SELECT b.*, r.roleName FROM user_base b, user_role r WHERE b.userRole = r.id");
            List<Object> paramsList = new ArrayList<>();
            if (username != null) {
                paramsList.add("%"+username+"%");
                sql.append(" AND b.userName LIKE ?");
            }
            if (roleId != 0) {
                paramsList.add(roleId);
                sql.append(" AND b.userRole = ?");
            }
            if (startDate != null && endDate != null) {
                paramsList.add(startDate);
                paramsList.add(endDate);
                sql.append(" AND b.createDate >= ? AND b.createDate <= ?");
            }
            if (pageSize != 0 && pageNum != 0) {
                paramsList.add((pageNum - 1) * pageSize);
                paramsList.add(pageSize);
                sql.append(" LIMIT ?, ?");
            }
            Object[] paramsArr = paramsList.toArray();
            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sql.toString());
            ResultSet resultSet = BaseDao.query(statement, paramsArr);
            while (resultSet.next()) {
                UserBase userBase = new UserBase();
                userBase.setUserName(resultSet.getString("userName"));
                userBase.setUserCode(resultSet.getLong("userCode"));
                userBase.setGender(resultSet.getBoolean("gender"));
                userBase.setBirthday(resultSet.getDate("birthday"));
                userBase.setPhone(resultSet.getString("phone"));
                userBase.setAddress(resultSet.getString("address"));
                userBase.setUserRole(resultSet.getLong("userRole"));
                userBase.setCreateBy(resultSet.getLong("createBy"));
                userBase.setCreateDate(resultSet.getDate("createDate"));
                userBase.setModifyBy(resultSet.getLong("modifyBy"));
                userBase.setModifyDate(resultSet.getDate("modifyDate"));
                userBase.setUserRoleName(resultSet.getString("roleName"));
                userList.add(userBase);
            }
            BaseDao.closeResources(null, statement, resultSet);
        }
        return userList;
    }

    /**
     * 向数据库中插入用户数据
     * @param connection
     * @param createKeyArr
     * @param createValueArr
     * @return
     * @throws SQLException
     */
    public boolean createUser(Connection connection, String[] createKeyArr, Object[] createValueArr) throws SQLException {
        if (connection != null) {
            // 构造sql
            StringBuilder sql = new StringBuilder("INSERT INTO user_base ");
            StringBuilder sqlKey = new StringBuilder("(");
            StringBuilder sqlValue = new StringBuilder("(");

            // 构造参数
            List<Object> params = new ArrayList<>();

            for (int i = 0; i < createKeyArr.length; i++) {
                if (i < (createKeyArr.length - 1)) {
                    sqlKey.append(createKeyArr[i] + ",");
                    sqlValue.append("?" + ",");
                } else if (i == createKeyArr.length - 1) {
                    sqlKey.append(createKeyArr[i]);
                    sqlValue.append("?");
                }

                if (i <= (createKeyArr.length - 1)) {
                    params.add(createValueArr[i]);
                }
            }
            sqlKey.append(")");
            sqlValue.append(")");
            sql.append(sqlKey);
            sql.append(" VALUES ");
            sql.append(sqlValue);

            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sql.toString());
            int updateIndex = BaseDao.update(statement, params.toArray());
            if (updateIndex > 0) {
                return true;
            }
            BaseDao.closeResources(null, statement, null);
        }
        return false;
    }
}
