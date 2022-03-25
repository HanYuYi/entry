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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        Connection database = (Connection) servletContext.getAttribute("database");
        System.out.println(database);

        String username = req.getParameter("username");

        try {
            try (PreparedStatement preparedStatement = database.prepareStatement("SELECT * FROM students WHERE name = ?")) {
                preparedStatement.setObject(1, username);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    List<LoginMapping> students = new ArrayList();
                    while (result.next()) {
                        students.add(new LoginMapping(
                                result.getString("name"),
                                result.getString("gender"),
                                result.getInt("score")
                        ));
                    }
                    PrintWriter writer = resp.getWriter();
                    writer.write("" + writer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
