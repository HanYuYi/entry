package com.HanYuYi.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的公共类
 */
public class BaseDao {
    private static String driver;
    private static String url;
    private static String name;
    private static String password;

    // 类加载的时候就会执行静态代码块
    static {
        Properties properties = new Properties();
        InputStream resource = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(resource);
            driver = properties.getProperty("DRIVER");
            url = properties.getProperty("DATABASE_URL");
            driver = properties.getProperty("DATABASE_USERNAME");
            driver = properties.getProperty("DATABASE_PASSWORD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, name, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    /**
     * 公共查询方法
     * @param sql
     * @param params
     * @return
     */
    public static ResultSet query(String sql, Object[] params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            ResultSet result = statement.executeQuery();
            result.close();
            statement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 公共更新方法
     * @param sql
     * @param params
     * @return
     */
    public static int update(String sql, Object[] params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            int UpdaterowIndex = statement.executeUpdate();
            statement.close();
            connection.close();
            return UpdaterowIndex;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
