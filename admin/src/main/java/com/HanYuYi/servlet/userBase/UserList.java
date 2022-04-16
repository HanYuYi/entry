package com.HanYuYi.servlet.userBase;

import com.HanYuYi.entity.UserBase;
import com.HanYuYi.entity.UserRole;
import com.HanYuYi.service.userBase.UserBaseServiceImpl;
import com.HanYuYi.service.userRole.UserRoleServiceImpl;
import com.HanYuYi.util.Constants;
import com.HanYuYi.util.DataFormatConversion;
import com.HanYuYi.util.DateUtils;
import com.HanYuYi.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        String _username = null;
        if (!StringUtils.isNullOrEmpty(username)) {
            _username = username;
        }
        long _roleId = 0l;
        if (!StringUtils.isNullOrEmpty(roleId)) {
            _roleId = Integer.parseInt(roleId);
        }
        // 时间必须成对传
        String _startDate = null;
        String _endDate = null;
        Long startMilli = null;
        Long endMilli = null;
        if (!StringUtils.isNullOrEmpty(startDate) && !StringUtils.isNullOrEmpty(endDate)) {
            LocalDateTime parseStartDate = DateUtils.dateTimeStringToDate(startDate);
            LocalDateTime parseEndDate = DateUtils.dateTimeStringToDate(endDate);
            startMilli = DateUtils.dateToEpochMilli(parseStartDate);
            endMilli = DateUtils.dateToEpochMilli(parseEndDate);
            if (startMilli <= endMilli) {
                _startDate = startDate;
                _endDate = endDate;
            }
        }
        int _pageSize = 20;
        if (!StringUtils.isNullOrEmpty(pageSize)) {
            _pageSize = Integer.parseInt(pageSize);
        }
        int _pageNum = 1;
        if (!StringUtils.isNullOrEmpty(pageNum)) {
            _pageNum = Integer.parseInt(pageNum);
        }
        HttpSession session = req.getSession();

        // 用户角色
        List<UserRole> roleList = new UserRoleServiceImpl().getRoleList();
        String roleJson = DataFormatConversion.Deserialization(roleList);
        session.setAttribute(Constants.ROLE_LIST, roleJson);
        // 用户列表
        UserBaseServiceImpl userBase = new UserBaseServiceImpl();
        List<UserBase> userList = userBase.getUserList(_username, _roleId, _startDate, _endDate, _pageSize, _pageNum);
        String userListJson = DataFormatConversion.Deserialization(userList);
        session.setAttribute(Constants.USER_LIST, userListJson);
        // 数据total
        int count = userBase.getUserCount(_username, _roleId, _startDate, _endDate);
        session.setAttribute(Constants.USER_LIST_LENGTH, count);

        // 查询条件的参数
        session.setAttribute("p_username", _username);
        session.setAttribute("p_roleId",  _roleId);
        session.setAttribute("p_startDate", startMilli);
        session.setAttribute("p_endDate", endMilli);
        session.setAttribute("p_pageSize", _pageSize);
        session.setAttribute("p_pageNum", _pageNum);

        resp.sendRedirect(req.getContextPath() + "/auth/user-list.jsp");
    }
}
