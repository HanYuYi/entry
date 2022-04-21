package com.HanYuYi.service.userBase;

import com.HanYuYi.dao.BaseDao;
import com.HanYuYi.dao.userBase.UserBaseDaoImpl;
import com.HanYuYi.entity.UserBase;
import com.HanYuYi.util.RespFormat;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * 用户逻辑层
 */
public class UserBaseServiceImpl implements UserBaseService{
    private UserBaseDaoImpl userInfo;

    private String username;
    private String password;
    private String confirmPassword;

    public UserBaseServiceImpl() {
        userInfo = new UserBaseDaoImpl();
    }

    public UserBaseServiceImpl(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    /**
     * 用户登录接口
     * @param username
     * @param password
     * @return
     */
    @Override
    public UserBase login(String username, String password) {
        Connection connection = null;
        UserBase info = null;
        try {
            connection = BaseDao.getConnection();
            UserBase temporaryInfo = userInfo.getUserInfo(connection, username);
            if (temporaryInfo != null && temporaryInfo.getUserPassword().equals(password)) {
                info = temporaryInfo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return info;
    }

    /**
     * 验证两次密码的输入是否一致
     * @return
     */
    @Override
    public boolean isAgreement() {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }

    /**
     * 用户是否存在
     * @param user
     * @return
     */
    @Override
    public boolean hasUsername(UserBaseDaoImpl user) {
        Connection connection = null;
        boolean has = false;
        try {
            connection = BaseDao.getConnection();
            if (user.hasUser(connection, username)) {
                has = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return has;
    }

    /**
     * 修改密码
     * @return
     */
    @Override
    public Map setPassword() {
        Map<String, Object> map = new HashMap<>();

        // 先验证密码
        if (!isAgreement()) {
            map.put("status", RespFormat.ERROR_STATUS);
            map.put("message", "两次输入的密码不一致");
            return map;
        }
        // 再验证用户是否存在
        UserBaseDaoImpl user = new UserBaseDaoImpl();
        if (!hasUsername(user)) {
            map.put("status", RespFormat.ERROR_STATUS);
            map.put("message", "用户不存在");
            return map;
        }
        // 最后验证修改是否成功
        boolean bool = false;
        Connection connection = BaseDao.getConnection();
        try {
            connection.setAutoCommit(false);
            bool = user.setUserPassword(connection, username, password);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            BaseDao.closeResources(connection, null, null);
        }

        if (bool) {
            map.put("status", RespFormat.SUCCESS_STATUS);
            map.put("message", "密码修改成功");
        } else {
            map.put("status", RespFormat.ERROR_STATUS);
            map.put("message", "密码修改异常");
        }
        return map;
    }

    /**
     * 查询用户数量
     * @return
     */
    @Override
    public int getUserCount(String username, long roleId, String startDate, String endDate) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            count = userInfo.userCount(connection, username, roleId, startDate, endDate);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return count;
    }

    /**
     * 用户列表查询用户信息
     * @param username
     * @param roleId
     * @param startDate
     * @param endDate
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<UserBase> getUserList(String username, long roleId, String startDate, String endDate, int pageSize, int pageNum, String sortColumn, Integer order) {
        Connection connection = null;
        List<UserBase> list = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            list = userInfo.userList(connection, username, roleId, startDate, endDate, pageSize, pageNum, sortColumn, order);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return list;
    }

    /**
     * 创建用户账号
     * @param username
     * @param gender
     * @param birthday
     * @param phone
     * @param address
     * @param userRole
     * @return
     */
    @Override
    public boolean toCreateUser(String username, String password, int gender, String birthday, String phone, String address, long userRole, long createBy, long modifyBy) {
        Connection connection = null;
        boolean isSuccess = false;

        // 用户编码逻辑
        int userCount = getUserCount(null, 0, null, null);
        userCount += 1;

        // 用户性别逻辑
        boolean genderBool = gender == 1;

        // 用户生日时间、创建时间、更新时间逻辑
        java.util.Date birthdayUtilDate = null;
        java.util.Date createUtilDate = null;
        java.util.Date modifyUtilDate = null;
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthdayUtilDate = simpleDate.parse(birthday);
            createUtilDate = simpleDate.parse(LocalDate.now().toString());
            modifyUtilDate = simpleDate.parse(LocalDate.now().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date birthdaySqlDate = new java.sql.Date(birthdayUtilDate.getTime());
        java.sql.Date createSqlDate = new java.sql.Date(createUtilDate.getTime());
        java.sql.Date modifySqlDate = new java.sql.Date(modifyUtilDate.getTime());

        String[] keyArr = { "userCode", "userName", "userPassword", "gender", "birthday", "phone", "address", "userRole", "createBy", "createDate", "modifyBy", "modifyDate" };
        Object[] valueArr = { userCount, username, password, genderBool, birthdaySqlDate, phone, address, userRole, createBy, createSqlDate, modifyBy, modifySqlDate };

        try {
            connection = BaseDao.getConnection();
            // 设置关闭事务自动提交
            connection.setAutoCommit(false);
            // 事务
            isSuccess = userInfo.createUser(connection, keyArr, valueArr);
            // 提交事务
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            BaseDao.closeResources(connection, null, null);
        }

        return isSuccess;
    }

    /**
     * 根据id更新用户信息
     * @param columns
     * @param id
     * @return
     */
    @Override
    public boolean toUpdateUser(Map<String, Object> columns, long id) {
        Connection connection = null;
        boolean status = false;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            status = userInfo.updateUser(connection, columns, id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return status;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public boolean toDeleteUser(long id) {
        Connection connection = null;
        boolean status = false;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            status = userInfo.deleteUser(connection, id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return status;
    }
}
