package com.HanYuYi.service.userBase;

import com.HanYuYi.entity.UserBase;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserBaseServiceImplTest {


    @Test
    void getUserCount() {
        int userCount = new UserBaseServiceImpl().getUserCount("adm", 0, null, null);
        System.out.println(userCount);
    }

    @Test
    void getUserList() {
        List<UserBase> list = new UserBaseServiceImpl().getUserList("admi", 0, null, null, 10, 1);
        list.stream().forEach(System.out::println);
    }

}