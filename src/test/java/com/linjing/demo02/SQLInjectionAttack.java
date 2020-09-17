package com.linjing.demo02;

import com.linjing.demo02.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionAttack {
    public static void main(String[] args) {
        //正常登陆
        //        login("jerry", "jerry@gmail.com");
        //SQL注入, 非法操作 -> statement不安全
        login("' or '1=1", "' or '1=1");
    }

    public static void login(String username, String password) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection(); //获取数据库连接
            statement = connection.createStatement();
            //SELECT * FROM user WHERE name = '' AND email = ''
            //SELECT * FROM user WHERE name = '' or '1=1' AND email = ''
            String sql = "SELECT * FROM user WHERE name = '" + username + "' AND email = '" + password + "'";
            resultSet = statement.executeQuery(sql); //查询
            while (resultSet.next()) {
                System.out.println("id:" + resultSet.getInt("id"));
                System.out.println("name:" + resultSet.getString("name"));
                System.out.println("email:" + resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, statement, resultSet);
        }
    }
}
