package com.HanYuYi.servlet.userBase;

import com.HanYuYi.entity.UserBase;
import com.HanYuYi.service.userBase.UserBaseServiceImpl;
import com.HanYuYi.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Object attr = req.getSession().getAttribute(Constants.USER_SESSION);
        // 未登录
        if (attr == null) {
            UserBaseServiceImpl userBaseService = new UserBaseServiceImpl();
            UserBase info = userBaseService.login(username, password);
            // 用户登录成功
            if (info != null) {
                req.getSession().setAttribute(Constants.USER_SESSION, info);
                req.getSession().setMaxInactiveInterval(60 * 60 * 12);
            } else {
                req.getSession().setAttribute(Constants.USER_ERROR, "用户名或密码错误");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/auth/home.jsp");
        }
    }
}
