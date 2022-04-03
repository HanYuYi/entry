package com.HanYuYi.web.user;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC实现事务
 */
@WebServlet("/transfer")
public class TransferAccounts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 待解决
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        ServletContext context = req.getServletContext();
        String amount = req.getParameter("amount");
        int amountNum = Integer.parseInt(amount);

        Connection database = (Connection) context.getAttribute("database");


        try {
            // 设置关闭事务自动提交
            database.setAutoCommit(false);
            // 减分
            String sql1 = "UPDATE students SET score = score - ? WHERE id = 2";
            PreparedStatement preparedStatement1 = database.prepareStatement(sql1);
            preparedStatement1.setInt(1, amountNum);
            preparedStatement1.executeUpdate();

            //制造错误
            // int i = 1/ 0;

            // 加分
            String sql2 = "UPDATE students SET score = score + ? WHERE id = 10";
            PreparedStatement preparedStatement2 = database.prepareStatement(sql2);
            preparedStatement2.setInt(1, amountNum);
            preparedStatement2.executeUpdate();
            // 提交事务
            database.commit();

            PrintWriter writer = resp.getWriter();
            writer.write("转分成功...");
            preparedStatement2.close();
            preparedStatement1.close();

        } catch (SQLException throwables) {
            // 回滚事务
            try {
                database.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }
}
