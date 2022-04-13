package com.HanYuYi.dao.userBase;

import com.HanYuYi.entity.UserBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface UserBaseDao {
    /**
     * 根据单个用户名查询用户信息
     * @param username
     * @return
     * @throws SQLException
     */
    UserBase getUserInfo(Connection connection, String username) throws SQLException;

    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
    boolean setUserPassword(Connection connection, String username, String password);

    /**
     * 查询用户是否存在
     * @param username
     * @return
     */
    boolean hasUser(Connection connection, String username) throws SQLException;

    /**
     * 查询用户数量
     * @return
     * @throws SQLException
     */
    int userCount(Connection connection) throws SQLException;

    /**
     * 查询所有用户信息
     * @param connection
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UserBase> allUserList(Connection connection, int pageSize, int pageNum);

    /**
     * 根据 用户名、用户角色、注册日期 分页查询用户信息
     * @return
     * @throws SQLException
     */
    List<UserBase> userList(
            Connection connection,
            String username,
            long roleId,
            Date StartDate,
            Date endDate,
            int pageSize,
            int pageNum) throws SQLException;
}
