package com.HanYuYi.service.userBase;

import com.HanYuYi.dao.userBase.UserBaseDaoImpl;
import com.HanYuYi.entity.UserBase;

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
    int getUserCount(String username, long roleId, String startDate, String endDate);

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
    List<UserBase> getUserList(String username, long roleId, String startDate, String endDate, int pageSize, int pageNum);

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
    boolean toCreateUser(String username, String password, int gender, String birthday, String phone, String address, long userRole, long createBy, long modifyBy);

    /**
     * 根据id更新用户信息
     * @param columns
     * @param id
     * @return
     */
    boolean toUpdateUser(Map<String, Object> columns, long id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean toDeleteUser(long id);
}
