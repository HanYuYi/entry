package com.HanYuYi.web.listener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 建立数据库连接
 */
public class DatabaseInit {

    /**
     * 获取数据库配置文件的值
     * @return
     */
    private Map<String, String> loadConfig() {
        HashMap<String, String> configMap = new HashMap<>();
        Properties properties = new Properties();
        try {
            System.out.println("000000000000000");
            FileInputStream fileInputStream = new FileInputStream("/database.properties");
            System.out.println("1234578");
            System.out.println(fileInputStream);
            properties.load(fileInputStream);
            configMap.put("url", properties.getProperty("DATABASE_URL"));
            configMap.put("username", properties.getProperty("DATABASE_USERNAME"));
            configMap.put("password", properties.getProperty("DATABASE_PASSWORD"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configMap;
    }

    /**
     * 建立数据库连接
     * @return
     */
    public Connection start() {
        Connection connection = null;
        Map<String, String> configMap = loadConfig();

        try {
           connection = DriverManager.getConnection(
                    configMap.get("url"),
                    configMap.get("username"),
                    configMap.get("password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
