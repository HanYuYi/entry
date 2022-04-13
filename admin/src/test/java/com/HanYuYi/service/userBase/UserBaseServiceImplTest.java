package com.HanYuYi.service.userBase;

import org.junit.jupiter.api.Test;

class UserBaseServiceImplTest {


    @Test
    void getUserCount() {
        int userCount = new UserBaseServiceImpl().getUserCount();
        System.out.println(userCount);
    }
}