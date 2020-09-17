package com.linjing.demo03;

import com.linjing.demo02.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestInsert {
    public static void main(String[] args) {
        //JDBCUtils静态方法块已经做好了mysql驱动加载

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection(); //获取数据库连接

            //?:使用问号占位符代替参数
            String sql = "INSERT INTO user (id, name, email) VALUES (?,?,?)";

            //区别：
            //预编译sql, 但是不执行
            pst = connection.prepareStatement(sql);

            //手动给参数赋值
            pst.setInt(1, 6); //id
            pst.setString(2, "lisa"); //name
            pst.setString(3, "lisa@gmail.com"); //email
            //sql.Date
            //util.Date

            //执行
            int updateRow = pst.executeUpdate();
            if (updateRow > 0) {
                System.out.println("插入成功！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, pst, null);
        }

    }
}
