package com.HanYuYi.service;

import com.HanYuYi.entity.UserBase;
import com.HanYuYi.service.userBase.UserBaseServiceImpl;
import org.junit.jupiter.api.Test;

class UserBaseServiceImplTest {

    @Test
    void login() {
        UserBase admin = new UserBaseServiceImpl().login("admin", "123456");
        System.out.println(admin);
    }
}