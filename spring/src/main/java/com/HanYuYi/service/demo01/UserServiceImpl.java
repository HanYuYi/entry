package com.HanYuYi.service.demo01;

import com.HanYuYi.dao.UserDao;

/**
 * 以bean的写法写了之后，用spring赋值
 */
public class UserServiceImpl implements UserService {
    private String name;
    private UserDao userDao;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUserDao() {
        userDao.getUser();
    }

    @Override
    public void show() {
        System.out.println(name + "你好！user被执行了！");
    }

    @Override
    public String toString() {
        return "UserServiceImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
