package com.HanYuYi.service.userBase;

import com.HanYuYi.dao.userBase.UserBaseDaoImpl;
import com.HanYuYi.entity.UserBase;

import java.sql.SQLException;

/**
 * 逻辑层，根据用户名、密码登录
 */
public class UserBaseServiceImpl implements UserBaseService{
    private UserBaseDaoImpl userInfo;

    public UserBaseServiceImpl() {
        userInfo = new UserBaseDaoImpl();
    }

    @Override
    public UserBase login(String username, String password) {
        UserBase info = null;
        try {
            UserBase temporaryInfo = userInfo.getUserInfo(username);
            if (temporaryInfo != null && temporaryInfo.getUserPassword().equals(password)) {
                info = temporaryInfo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }
}
