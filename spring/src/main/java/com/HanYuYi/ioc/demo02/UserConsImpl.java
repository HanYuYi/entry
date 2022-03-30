package com.HanYuYi.ioc.demo02;

public class UserConsImpl implements UserCons {

    private String username;

    private UserAutoNew names;

    public UserConsImpl(String username) {
        this.username = username;
    }

    public UserConsImpl(UserAutoNew names) {
        this.names = names;
    }

    @Override
    public void show() {
        System.out.println(username + " 你好，这里是无参构造篇！");
    }
}
