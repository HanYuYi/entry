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
            name = properties.getProperty("DATABASE_USERNAME");
            password = properties.getProperty("DATABASE_PASSWORD");
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 获取PreparedStatement sql预编译
     * @param connection
     * @param sql
     * @return
     */
    public static PreparedStatement getPreparedStatement(Connection connection, String sql) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    /**
     * 公共查询方法
     * @param statement
     * @param params
     * @return
     */
    public static ResultSet query(PreparedStatement statement, Object[] params) {
        ResultSet result = null;
        try {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 公共更新方法
     * @param statement
     * @param params
     * @return
     */
    public static int update(PreparedStatement statement, Object[] params) {
        int updateRowIndex = -1;
        try {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            updateRowIndex = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateRowIndex;
    }

    /**
     * 关闭数据库连接
     * @param connection
     * @param statement
     * @param result
     * @return
     */
    public static boolean closeResources(Connection connection, PreparedStatement statement, ResultSet result) {
        boolean status = true;

        try {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }
}
