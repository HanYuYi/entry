package com.HanYuYi.service.userBase;

import com.HanYuYi.dao.userBase.UserPasswordChangeDaoImpl;
import com.HanYuYi.util.RespFormat;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 修改密码
 */
public class UserPasswordChangeImpl implements UserPasswordChange {
    private String username;
    private String password;
    private String confirmPassword;

    public UserPasswordChangeImpl(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean isAgreement() {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasUsername(UserPasswordChangeDaoImpl user) {
        boolean has = false;
        try {
            if (user.hasUser(username)) {
                has = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return has;
    }



    @Override
    public Map setPassword() {
        Map<String, Object> map = new HashMap<>();

        // 先验证密码
        if (!isAgreement()) {
            map.put("status", RespFormat.ERROR_STATUS);
            map.put("message", "两次输入的密码不一致");
            return map;
        }
        // 再验证用户是否存在
        UserPasswordChangeDaoImpl userPasswordChange = new UserPasswordChangeDaoImpl();
        if (!hasUsername(userPasswordChange)) {
            map.put("status", RespFormat.ERROR_STATUS);
            map.put("message", "用户不存在");
            return map;
        }
        // 最后验证修改是否成功
        boolean bool = userPasswordChange.setUserPassword(username, password);
        if (bool) {
            map.put("status", RespFormat.SUCCESS_STATUS);
            map.put("message", "密码修改成功");
        } else {
            map.put("status", RespFormat.ERROR_STATUS);
            map.put("message", "密码修改异常");
        }
        return map;
    }
}
