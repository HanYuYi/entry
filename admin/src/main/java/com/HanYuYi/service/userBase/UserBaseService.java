package com.HanYuYi.service.userBase;

import com.HanYuYi.entity.UserBase;

public interface UserBaseService {
    /**
     * 用户登录接口
     * @param username
     * @param password
     * @return
     */
    public UserBase login(String username, String password);
}
