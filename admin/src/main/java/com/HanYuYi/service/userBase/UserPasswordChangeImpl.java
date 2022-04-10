package com.HanYuYi.service.userBase;

/**
 * 修改密码
 */
public class UserPasswordChangeImpl implements UserPasswordChange {
    private String username;
    private String password;
    private String confirmPassword;

    public UserPasswordChangeImpl(String username, String password, String confirmPassword) {
        this.username = username;
        this.username = password;
        this.username = confirmPassword;
    }

    @Override
    public boolean isAgreement() {
        return false;
    }

    @Override
    public boolean hasUsername() {
        return false;
    }

    @Override
    public String setPassword() {
        return "修改成功";
    }
}
