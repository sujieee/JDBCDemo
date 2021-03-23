package com.jie.demo1;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * PreparedStatementTest
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/23
 */
public class PreparedStatementTest {
    public void insertUseStatement() throws Exception {
        long start = System.currentTimeMillis();
        try (
                Statement statement = ConnUtils.createState();
        ) {
            for (int i = 0; i < 100; i++) {
                statement.executeUpdate("insert into student values(null,'mario"+i+"',20,1)");
            }
            System.out.println("使用Statement费时：" + (System.currentTimeMillis() - start));
        }
    }

    public void insertUsePreparedStatement() throws Exception {
        long start = System.currentTimeMillis();
        try (
                PreparedStatement preparedStatement = ConnUtils.createPreparedState("insert into student values(null,?,20,1)");
        ) {
            for (int i = 0; i < 100; i++) {
                preparedStatement.setString(1, "mario" + i);
                preparedStatement.executeUpdate();
            }
            System.out.println("使用PreparedStatement费时：" + (System.currentTimeMillis() - start));
        }
    }

    public static void main(String[] args) throws Exception {
        PreparedStatementTest test = new PreparedStatementTest();
        test.insertUseStatement();
        test.insertUsePreparedStatement();
    }
}
