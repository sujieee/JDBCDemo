package com.jie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ConnMySql
 *
 * @author J
 * @version 1.0
 * @since 2021/3/17 22:44
 */
public class ConnMySql {
    public static void main(String[] args) throws Exception {
        //1.加载驱动，使用反射知识
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                //2.使用DriverManager获取数据库连接，Connection就代表了Java程序和数据库的连接
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "sj20010117");
                //3.创建一个Statement对象
                Statement statement = connection.createStatement();
                //4.执行查询sql语句
                ResultSet rs = statement.executeQuery("select s.*,t.name from student s,teacher t where  t.id=s.teacher_id");
        ) {
            //如果移动之后ResultSet记录指针依然指向有效行，则next()方法返回true
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t" + rs.getString(5));
            }
        }
    }
}
