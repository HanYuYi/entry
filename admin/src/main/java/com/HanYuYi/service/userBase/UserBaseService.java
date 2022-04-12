package com.HanYuYi.service.userBase;

import com.HanYuYi.dao.userBase.UserBaseDaoImpl;
import com.HanYuYi.entity.UserBase;

import java.util.Map;

public interface UserBaseService {
    /**
     * 用户登录接口
     * @param username
     * @param password
     * @return
     */
    public UserBase login(String username, String password);

    /**
     * 验证两次密码的输入是否一致
     * @return
     */
    public boolean isAgreement();

    /**
     * 用户是否存在
     * @return
     */
    public boolean hasUsername(UserBaseDaoImpl user);

    /**
     * 执行修改密码
     * @return
     */
    public Map setPassword();
}
