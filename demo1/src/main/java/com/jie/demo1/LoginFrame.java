package com.jie.demo1;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * LoginFrame
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/23
 */
public class LoginFrame {
    // 显示界面的GUI组件
    private JFrame jFrame = new JFrame("登录");
    private JTextField userField = new JTextField(20);
    private JTextField passField = new JTextField(20);
    private JButton loginButton = new JButton("登录");

    // 验证登录
    public boolean validateUseStatement(String username, String password) throws Exception {
        // 执行查询的sql
        // 此处如果sql注入，username输入' or true or '则也会查询成功，安全性不高
        String sql = "select * from jdbc_test where jdbc_name = '" + username + "' and jdbc_desc = '" + password + "'";
        System.out.println(sql);
        try (
                Statement statement = ConnUtils.createState();
                ResultSet rs = statement.executeQuery(sql);
        ) {
            // 如果查询的ResultSet里面有超过一条的记录，则登录成功
            if (rs.next()) {
                return true;
            }
            return false;
        }
    }

    public boolean validateUsePreparedStatement(String username, String password) throws Exception {
        // 执行查询的sql
        // 使用预编译sql安全性较高
        String sql = "select * from jdbc_test where jdbc_name = ? and jdbc_desc = ?";
        System.out.println(sql);
        try (
                PreparedStatement ps = ConnUtils.createPreparedState(sql);
        ) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (
                    ResultSet rs = ps.executeQuery()
            ) {
                // 如果查询的ResultSet里面有超过一条的记录，则登录成功
                if (rs.next()) {
                    return true;
                }
                return false;
            }
        }
    }

    public void initUseStatement() throws Exception {
        // 为登录按钮添加事件监听器
        loginButton.addActionListener(e -> {
            // 登录成功则提示"登录成功"
            try {
                if (validateUseStatement(userField.getText(), passField.getText())) {
                    JOptionPane.showMessageDialog(jFrame, "登录成功");
                } else {
                    JOptionPane.showMessageDialog(jFrame, "登录失败");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        jFrame.add(userField, BorderLayout.NORTH);
        jFrame.add(passField);
        jFrame.add(loginButton, BorderLayout.SOUTH);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void initUsePreparedStatement() throws Exception {
        // 为登录按钮添加事件监听器
        loginButton.addActionListener(e -> {
            // 登录成功则提示"登录成功"
            try {
                if (validateUsePreparedStatement(userField.getText(), passField.getText())) {
                    JOptionPane.showMessageDialog(jFrame, "登录成功");
                } else {
                    JOptionPane.showMessageDialog(jFrame, "登录失败");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        jFrame.add(userField, BorderLayout.NORTH);
        jFrame.add(passField);
        jFrame.add(loginButton, BorderLayout.SOUTH);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
//        new LoginFrame().initUseStatement();
        new LoginFrame().initUsePreparedStatement();
    }
}
