package com.HanYuYi.servlet.userBase;

import com.HanYuYi.entity.UserRole;
import com.HanYuYi.service.userRole.UserRoleServiceImpl;
import com.HanYuYi.util.Constants;
import com.HanYuYi.util.DataFormatConversion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/userList.do")
public class UserList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // 用户角色
        List<UserRole> roleList = new UserRoleServiceImpl().getRoleList();
        String roleJson = DataFormatConversion.Deserialization(roleList);
        session.setAttribute(Constants.ROLE_LIST, roleJson);

        resp.sendRedirect(req.getContextPath() + "/auth/user-list.jsp");
    }
}
