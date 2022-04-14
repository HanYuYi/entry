package com.HanYuYi.dao.userRole;

import com.HanYuYi.dao.BaseDao;
import com.HanYuYi.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao {
    /**
     * 查询用户角色
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public List<UserRole> roleList(Connection connection) throws SQLException {
       List<UserRole> list = new ArrayList<>();

        if (connection != null) {
            String sql = "SELECT * FROM user_role";
            PreparedStatement statement = BaseDao.getPreparedStatement(connection, sql);
            ResultSet resultSet = BaseDao.query(statement, null);
            while (resultSet.next()) {
                UserRole userRole = new UserRole();
                userRole.setId(resultSet.getLong("id"));
                userRole.setRoleCode(resultSet.getLong("roleCode"));
                userRole.setRoleName(resultSet.getString("roleName"));
                userRole.setCreateBy(resultSet.getLong("createBy"));
                userRole.setCreateBydate(resultSet.getDate("createDate"));
                userRole.setModifyBy(resultSet.getLong("modifyBy"));
                userRole.setModifyDate(resultSet.getDate("modifyDate"));
                list.add(userRole);
            }
            BaseDao.closeResources(null, statement, resultSet);
        }
        return list;
    }
}
