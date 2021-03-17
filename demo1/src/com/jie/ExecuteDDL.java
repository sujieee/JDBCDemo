package com.jie;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * ExecuteDDL
 *
 * @author J
 * @version 1.0
 * @since 2021/3/17 23:01
 */
public class ExecuteDDL {
    private String driver;
    private String url;
    private String username;
    private String password;

    public void initParam(String paramFile) throws Exception {
        //使用properties类来加载属性文件
        Properties properties = new Properties();
        properties.load(new FileInputStream(paramFile));
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    public void createTable(String sql) throws Exception {
        //加载驱动
        Class.forName(driver);
        try (
                //获取数据库连接
                Connection connection = DriverManager.getConnection(url,username,password);
                //使用Connection来创建一个Statement
                Statement statement = connection.createStatement();
        ){
            //执行DDL语句，创建数据库表
            statement.executeUpdate(sql);
        }
    }

    public static void main(String[] args) throws Exception {
        ExecuteDDL executeDDL = new ExecuteDDL();
        executeDDL.initParam("D:\\sujieee\\JDBCDemo\\demo1\\src\\com\\jie\\mysql.properties");
        executeDDL.createTable("create table jdbc_test(jdbc_id int auto_increment primary key,jdbc_name varchar(255),jdbc_desc text);");
        System.out.println("建表成功");
    }

}
