package com.linjing.demo04;

import com.linjing.demo02.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            //关闭数据库的自动提交功能, 会自动开启事物
            conn.setAutoCommit(false); //开启事物

            String sql1 = "UPDATE account SET value = value - 500 WHERE name = 'A'";
            pst = conn.prepareStatement(sql1);
            pst.executeUpdate();

            int x = 1 / 0; //报错

            String sql2 = "UPDATE account SET value = value + 500 WHERE name = 'B'";
            pst = conn.prepareStatement(sql2);
            pst.executeUpdate();

            //业务完毕，提交事物
            conn.commit();
            System.out.println("操作成功！");
        } catch (SQLException e) {
            //如果失败，默认回滚（可以不写rollback）

            //这里是显示定义。
            try {
                assert conn != null;
                //如果失败，就回滚事物
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JDBCUtils.release(conn, pst, rs);
        }
    }
}
