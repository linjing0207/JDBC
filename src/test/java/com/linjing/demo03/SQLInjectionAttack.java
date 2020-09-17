package com.linjing.demo03;

import com.linjing.demo02.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLInjectionAttack {
    public static void main(String[] args) {
        //正常登陆
        login("jerry", "jerry@gmail.com");
        //SQL注入, 非法操作 -> statement不安全
        //        login("' or '1=1", "' or '1=1");
    }

    public static void login(String username, String password) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection(); //获取数据库连接

            //PreparedStatement 防止SQL注入的本质：
            //把传入的参数当做字符，用引号包裹起来，假设存在转义字符，直接忽略。引号会被直接转义。
            String sql = "SELECT * FROM user WHERE name = ? AND email = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            resultSet = pst.executeQuery(); //查询
            while (resultSet.next()) {
                System.out.println("id:" + resultSet.getInt("id"));
                System.out.println("name:" + resultSet.getString("name"));
                System.out.println("email:" + resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, pst, resultSet);
        }
    }
}
