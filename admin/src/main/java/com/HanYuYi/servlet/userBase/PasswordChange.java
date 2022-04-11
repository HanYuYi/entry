package com.HanYuYi.servlet.userBase;

import com.HanYuYi.service.userBase.UserPasswordChangeImpl;
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
import java.util.Map;


@WebServlet("/user/passwordChange")
public class PasswordChange extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();

        ReqSerialize reqSerialize = DataFormatConversion.serialize(inputStream, new ReqSerialize());

        String username = reqSerialize.username;
        String password = reqSerialize.password;
        String confirmPassword = reqSerialize.confirmPassword;

        UserPasswordChangeImpl passwordChange = new UserPasswordChangeImpl(username, password, confirmPassword);

        resp.setContentType("application/json;charset=UTF-8");

        // 密码设置是否成功,有文案是成功，否则失败
        Map<String, Object> isSuccess = passwordChange.setPassword();
        // 返回的json
        String jsonData = DataFormatConversion.Deserialization(isSuccess);

        PrintWriter writer = resp.getWriter();
        writer.write(jsonData);
        // 因为json字符串是流，所以需要输出缓冲和关闭流
        writer.flush();
        writer.close();
    }
}

class ReqSerialize {
    public String username;
    public String password;
    public String confirmPassword;
}


