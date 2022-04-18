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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@WebServlet("/userList.do")
public class UserList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(Constants.USER_UPDATE_SUCCESS, "");
        req.getSession().setAttribute(Constants.USER_UPDATE_ERROR, "");

        String method = req.getParameter("method");
        if ("query".equals(method)) {
            query(req, resp);
        }
        if ("update".equals(method)) {
            update(req, resp);
        }
    }

    /**
     * 条件查询
     * @param req
     * @param resp
     * @throws IOException
     */
    private void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取查询参数
        String username = req.getParameter("username");
        String roleId = req.getParameter("roleId");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String pageSize = req.getParameter("pageSize");
        String pageNum = req.getParameter("pageNum");

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
            if (Integer.parseInt(pageSize) >= _pageSize) {
                _pageSize = Integer.parseInt(pageSize);
            }
        }
        int _pageNum = 1;
        if (!StringUtils.isNullOrEmpty(pageNum)) {
            if (Integer.parseInt(pageNum) >= _pageNum) {
                _pageNum = Integer.parseInt(pageNum);
            }
        }
        HttpSession session = req.getSession();

        // 用户角色
        List<UserRole> roleList = new UserRoleServiceImpl().getRoleList();
        if (roleList.size() > 0) {
            UserRole userRole = new UserRole();
            userRole.setId(0);
            userRole.setRoleCode(0);
            userRole.setRoleName("全部");
            roleList.add(userRole);
        }
        // 根据用户角色code正序排序
        roleList.sort((UserRole o1, UserRole o2) -> {
            if (o1.getRoleCode() == o2.getRoleCode()) {
                return 0;
            }
            return o1.getRoleCode() < o2.getRoleCode() ? -1 : 1;
        });
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


    /**
     * 编辑用户信息
     * @param req
     * @param resp
     */
    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String[] columnsArr = {"userName", "gender", "birthday", "userRole", "phone", "address"};
        HashMap<String, Object> columnsMap = new HashMap<>();

        for (String key : columnsArr) {
            String value = req.getParameter(key);
            if (!StringUtils.isNullOrEmpty(value)) {
                columnsMap.put(key, value);
            }
            System.out.println(value);
        }

        // 联系电话校验
        String _phone = (String) columnsMap.get("phone");
        if (!StringUtils.isNullOrEmpty(_phone)) {
            if (!_phone.matches("^[1]\\d{10}$")) {
                req.getSession().setAttribute(Constants.USER_UPDATE_ERROR, "你的手机号码格式有误");
                return;
            }
        }

        // 转换性别为boolean
        String _gender = (String) columnsMap.get("gender");
        if (!StringUtils.isNullOrEmpty(_gender)) {
            columnsMap.put("gender", "1".equals(_gender) ? true : false);
        }

        // 转换userRole为long
        String _userRole = (String) columnsMap.get("userRole");
        if (!StringUtils.isNullOrEmpty(_userRole)) {
            columnsMap.put("gender", (Long.parseLong(_userRole)));
        }

        // 处理生日时间、更新时间逻辑
        String _birthday = (String) columnsMap.get("birthday");
        java.util.Date birthdayUtilDate = null;
        java.util.Date modifyUtilDate = null;
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("birthday: " + _birthday);
        try {
            if (!StringUtils.isNullOrEmpty(_birthday)) {
                birthdayUtilDate = simpleDate.parse(_birthday);
                java.sql.Date birthdaySqlDate = new java.sql.Date(birthdayUtilDate.getTime());
                columnsMap.put("birthday", birthdaySqlDate);
            }
            modifyUtilDate = simpleDate.parse(LocalDate.now().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date modifySqlDate = new java.sql.Date(modifyUtilDate.getTime());

        columnsMap.put("modifyDate", modifySqlDate);

        UserBaseServiceImpl userBaseService = new UserBaseServiceImpl();
        boolean isSuccess = userBaseService.toUpdateUser(columnsMap, Long.parseLong(req.getParameter("id")));
        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/auth/user-list.jsp");
            req.getSession().setAttribute(Constants.USER_UPDATE_SUCCESS, "信息更新成功");
        } else {
            req.getSession().setAttribute(Constants.USER_UPDATE_ERROR, "用户信息更新失败，请重试");
        }
    }
}
