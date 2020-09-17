package com.linjing.demo03;

import com.linjing.demo02.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDelete {
    public static void main(String[] args) {
        //JDBCUtils静态方法块已经做好了mysql驱动加载

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection(); //获取数据库连接
            String sql = "DELETE FROM user WHERE id = ?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, 6);

            int updateRow = pst.executeUpdate();
            if (updateRow > 0) {
                System.out.println("删除成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, pst, null);
        }

    }
}
