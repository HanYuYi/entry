package com.HanYuYi.servlet.userBase;

import com.HanYuYi.entity.UserBase;
import com.HanYuYi.entity.UserRole;
import com.HanYuYi.service.userBase.UserBaseServiceImpl;
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
        // 获取查询参数
        String username = req.getParameter("username");
        String roleId = req.getParameter("roleId");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String pageSize = req.getParameter("pageSize");
        String pageNum = req.getParameter("pageNum");
        System.out.println("username：" + username);
        System.out.println("roleId：" + roleId);
        System.out.println("startDate：" + startDate);
        System.out.println("endDate：" + endDate);
        System.out.println("pageSize：" + pageSize);
        System.out.println("pageNum：" + pageNum);

        long _roleId = 0l;
        if (roleId != null) {
            _roleId = Integer.parseInt(roleId);
        }
        int _pageSize = 20;
        if (pageSize != null) {
            _pageSize = Integer.parseInt(pageSize);
        }
        int _pageNum = 1;
        if (pageSize != null) {
            _pageNum = Integer.parseInt(pageNum);
        }
        // 时间必须成对传
        /*if (startDate == null || endDate==null) {
            startDate = null;
            endDate = null;
        }*/
        HttpSession session = req.getSession();

        // 用户角色
        List<UserRole> roleList = new UserRoleServiceImpl().getRoleList();
        String roleJson = DataFormatConversion.Deserialization(roleList);
        session.setAttribute(Constants.ROLE_LIST, roleJson);

        // 用户列表
        UserBaseServiceImpl userBase = new UserBaseServiceImpl();
        List<UserBase> userList = userBase.getUserList(
                username,
                _roleId,
                null,
                null,
                _pageSize,
                _pageNum
        );
        String userListJson = DataFormatConversion.Deserialization(userList);
        session.setAttribute(Constants.USER_LIST, userListJson);

        // 数据total
        int count = userBase.getUserCount(username, _roleId, null, null);
        session.setAttribute(Constants.USER_LIST_LENGTH, count);

        resp.sendRedirect(req.getContextPath() + "/auth/user-list.jsp");
    }
}
