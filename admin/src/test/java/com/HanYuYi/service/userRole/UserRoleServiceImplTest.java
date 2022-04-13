package com.HanYuYi.service.userRole;

import com.HanYuYi.entity.UserRole;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleServiceImplTest {

    @Test
    void getRoleList() {
        List<UserRole> roleList = new UserRoleServiceImpl().getRoleList();
        for (UserRole role : roleList) {
            System.out.println(role.toString());
        }
    }
}