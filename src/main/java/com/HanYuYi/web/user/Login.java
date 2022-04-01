package com.HanYuYi.web.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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

        String username = req.getParameter("username");

        try {
            try (PreparedStatement preparedStatement = database.prepareStatement("SELECT * FROM students WHERE name = ?")) {
                preparedStatement.setString(1, username);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    List<LoginMapping> students = new ArrayList();
                    while (result.next()) {
                        System.out.println(result.getString("name"));
                        students.add(new LoginMapping(
                                result.getString("name"),
                                result.getString("gender"),
                                result.getInt("score")
                        ));
                    }
                    PrintWriter writer = resp.getWriter();
                    Object[] array = students.toArray();
                    writer.write(dataToJson(array));
                    for (LoginMapping s : students) {
                        System.out.println(s);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将查询出来的数据反序列化为json
     * @param data
     * @return
     */
    private String dataToJson(Object[] data) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String backStr = null;
        try {
            backStr = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return backStr;
    }
}
