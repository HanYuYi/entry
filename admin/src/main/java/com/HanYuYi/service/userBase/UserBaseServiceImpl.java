package com.HanYuYi.service.userBase;

import com.HanYuYi.dao.BaseDao;
import com.HanYuYi.dao.userBase.UserBaseDaoImpl;
import com.HanYuYi.entity.UserBase;
import com.HanYuYi.util.RespFormat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public boolean isAgreement() {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }

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
        Connection connection = BaseDao.getConnection();
        boolean bool = user.setUserPassword(connection, username, password);
        if (bool) {
            map.put("status", RespFormat.SUCCESS_STATUS);
            map.put("message", "密码修改成功");
        } else {
            map.put("status", RespFormat.ERROR_STATUS);
            map.put("message", "密码修改异常");
        }
        BaseDao.closeResources(connection, null, null);
        return map;
    }
}
