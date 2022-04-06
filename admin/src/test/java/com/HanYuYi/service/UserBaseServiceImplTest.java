package com.HanYuYi.service;

import com.HanYuYi.entity.UserBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserBaseServiceImplTest {

    @Test
    void login() {
        UserBase admin = new UserBaseServiceImpl().login("admin", "123456");
        System.out.println(admin);
    }
}