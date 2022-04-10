package com.HanYuYi.dao.userBase;

/**
 * 根据用户名更新用户密码
 */
public interface UserPasswordChangeDao {
    public boolean setUserPassword(String username, String password);
}
