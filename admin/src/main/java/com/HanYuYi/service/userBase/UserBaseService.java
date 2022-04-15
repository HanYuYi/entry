package com.HanYuYi.service.userBase;

import com.HanYuYi.dao.userBase.UserBaseDaoImpl;
import com.HanYuYi.entity.UserBase;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface UserBaseService {
    /**
     * 用户登录接口
     * @param username
     * @param password
     * @return
     */
    UserBase login(String username, String password);

    /**
     * 验证两次密码的输入是否一致
     * @return
     */
    boolean isAgreement();

    /**
     * 用户是否存在
     * @return
     */
    boolean hasUsername(UserBaseDaoImpl user);

    /**
     * 执行修改密码
     * @return
     */
    Map setPassword();

    /**
     * 获取用户数量
     * @return
     */
    int getUserCount(String username, long roleId, Long startDate, Long endDate);

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
    List<UserBase> getUserList(String username, long roleId, Long startDate, Long endDate, int pageSize, int pageNum);
}
