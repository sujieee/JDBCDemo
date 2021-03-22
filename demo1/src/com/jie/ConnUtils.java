package com.jie;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class ConnUtils {
    private static String driver;
    private static String username;
    private static String url;
    private static String password;

    public static void initParam() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("/home/sujie/mario/JDBCDemo/demo1/src/com/jie/mysql.properties"));
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    public static Statement createState() throws Exception {
        initParam();
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        return statement;
    }

}
