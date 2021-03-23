package com.jie.demo1;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class ConnUtils {

    public static Connection getConnection() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("/home/sujie/mario/JDBCDemo/demo1/src/main/resources/mysql.properties"));
        Class.forName(properties.getProperty("driver"));
        Connection connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
        return connection;
    }

    public static Statement createState() throws Exception {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement;
    }

    public static PreparedStatement createPreparedState(String sql) throws Exception {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps;
    }
}
