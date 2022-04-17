package com.HanYuYi.servlet.userBase;

import com.HanYuYi.entity.UserBase;
import com.HanYuYi.service.userBase.UserBaseServiceImpl;
import com.HanYuYi.util.Constants;
import com.HanYuYi.util.DataFormatConversion;
import com.HanYuYi.util.RespFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/user/createUser")
public class CreateUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        CreateUserReqSerialize serialize = DataFormatConversion.serialize(inputStream, new CreateUserReqSerialize());

        String username = serialize.username;
        String password = serialize.password;
        String confirmPassword = serialize.confirmPassword;
        int gender = serialize.gender;
        String birthday = serialize.birthday;
        long userRole = serialize.userRole;
        String phone = serialize.phone;
        String address = serialize.address;

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        HashMap<Object, Object> map = new HashMap<>();

        if (!confirmPassword.equals(password)) {
            map.put("status", RespFormat.ERROR_STATUS);
            map.put("message", "两次输入的密码不一致");
        } else if (!phone.matches("^[1]\\d{10}$")) {
            map.put("status", RespFormat.ERROR_STATUS);
            map.put("message", "你的手机号码格式有误");
        } else {
            UserBase currentUser = (UserBase)req.getSession().getAttribute(Constants.USER_SESSION);

            UserBaseServiceImpl userBaseService = new UserBaseServiceImpl();
            boolean createStatus = userBaseService.toCreateUser(username, password, gender, birthday, phone, address, userRole, currentUser.getId(), currentUser.getId());

            if (createStatus) {
                map.put("status", RespFormat.SUCCESS_STATUS);
                map.put("message", "用户创建成功");
            }
        }

        String deserialization = DataFormatConversion.Deserialization(map);
        writer.write(deserialization);
        writer.flush();

    }
}

/**
 * post参数格式
 */
class CreateUserReqSerialize{
    public String username;
    public String password;
    public String confirmPassword;
    public int gender;
    public String birthday;
    public long userRole;
    public String phone;
    public String address;
}
