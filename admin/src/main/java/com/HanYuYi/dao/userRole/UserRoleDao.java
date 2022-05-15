package com.HanYuYi.dao.userRole;

import com.HanYuYi.entity.UserRole;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserRoleDao {

    /**
     * 查询用户角色
     * @param connection
     * @return
     * @throws SQLException
     */
    List<UserRole> roleList(Connection connection) throws SQLException;
}
