package com.HanYuYi.aop02.springAop.example01.service;

public class UserServiceImpl implements UserService {
    @Override
    public void insert() {
        System.out.println("用户添加成功");
    }

    @Override
    public void delete() {
        System.out.println("用户删除成功");
    }

    @Override
    public void select() {
        System.out.println("用户查询成功");
    }

    @Override
    public void update() {
        System.out.println("用户信息更新成功");

    }
}
