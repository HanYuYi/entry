package com.HanYuYi.service.userBase;

/**
 * 修改密码，两个验证条件
 */
public interface UserPasswordChange {

    /**
     * 验证两次密码的输入是否一致
     * @return
     */
    public boolean isAgreement();

    /**
     * 用户是否存在
     * @return
     */
    public boolean hasUsername();

    /**
     * 执行修改密码
     * @return
     */
    public String setPassword();
}
