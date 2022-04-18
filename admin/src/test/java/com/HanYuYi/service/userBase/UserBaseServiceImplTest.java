package com.HanYuYi.service.userBase;

import com.HanYuYi.entity.UserBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UserBaseServiceImplTest {
    UserBaseService UserBaseServiceImpl = null;

    @BeforeEach
    void setUp() {
        UserBaseServiceImpl = new UserBaseServiceImpl();
    }


    @Test
    void getUserCount() {
        int userCount = UserBaseServiceImpl.getUserCount("adm", 0, "", "");
        System.out.println(userCount);
    }

    @Test
    void getUserList() {
        List<UserBase> list = UserBaseServiceImpl.getUserList("admi", 0, "", "", 10, 1);
        list.stream().forEach(System.out::println);
        for (UserBase u : list) {
            System.out.println(u.getCreateDateFmt());
        }
    }

    @Test
    void toCreateUser() {
        boolean status = UserBaseServiceImpl.toCreateUser("test001", "123456", 0, "2002-04-05", "17122312345", "成都市天河一路", 2l, 1l, 1l);
        System.out.println(status);
    }

    @Test
    void toUpdateUser() {
        Map<String, Object> columnsMap = new HashMap<>();
        columnsMap.put("phone", "13984712358");
        columnsMap.put("gender", true);
        boolean b = UserBaseServiceImpl.toUpdateUser(columnsMap, 2l);
        System.out.println(b);
    }
}