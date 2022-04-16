package com.HanYuYi.service.userRole;

import com.HanYuYi.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    /**
     * 查询全部用户角色
     * @return
     */
    List<UserRole> getRoleList();
}
