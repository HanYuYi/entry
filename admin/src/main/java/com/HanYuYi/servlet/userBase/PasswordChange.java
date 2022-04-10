package com.HanYuYi.servlet.userBase;

import com.HanYuYi.service.userBase.UserPasswordChangeImpl;
import com.HanYuYi.util.RespFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user/passwordChange")
public class PasswordChange extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        ServletInputStream inputStream = req.getInputStream();
        byte[] byt = new byte[1];
        int len = 0;
        while ((len = inputStream.read(byt)) != -1) {
            System.out.println(len);
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        System.out.println(username);
        System.out.println(password);
        System.out.println(confirmPassword);
        UserPasswordChangeImpl passwordChange = new UserPasswordChangeImpl(username, password, confirmPassword);
        // 密码设置是否成功,有文案是成功，否则失败
        String isSuccess = passwordChange.setPassword();

        resp.setContentType("application/json;charset=UTF-8");

        // 返回的json
        RespFormat<Object> respJson = new RespFormat<>();

        PrintWriter writer = resp.getWriter();
        if (isSuccess == null) {
            respJson.setStatus(RespFormat.SUCCESS_STATUS);
            respJson.setMessage("密码修改成功");
            String jsonData = respJson.getJsonData();
            writer.write(jsonData);
        } else {
            respJson.setStatus(RespFormat.ERROR_STATUS);
            respJson.setMessage(isSuccess);
            String jsonData = respJson.getJsonData();
            writer.write(jsonData);
        }
        // 因为json字符串是流，所以需要输出缓冲和关闭流
        writer.flush();
        writer.close();
    }
}
