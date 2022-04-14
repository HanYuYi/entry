package com.HanYuYi.service.userRole;

import com.HanYuYi.dao.BaseDao;
import com.HanYuYi.dao.userRole.UserRoleDaoImpl;
import com.HanYuYi.entity.UserRole;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserRoleServiceImpl implements UserRoleService{
    private UserRoleDaoImpl userRoleInfo;

    public UserRoleServiceImpl() {
        userRoleInfo = new UserRoleDaoImpl();
    }

    /**
     * 查询全部用户角色
     * @return
     */
    @Override
    public List<UserRole> getRoleList() {
        List<UserRole> userRoles = null;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            userRoles = userRoleInfo.roleList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return userRoles;
    }
}
