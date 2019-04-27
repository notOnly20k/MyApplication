package com.example.administrator.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOpenHelper {
    private static String driver = "com.mysql.jdbc.Driver";// MySQL驱动
    private static String url = "jdbc:mysql://10.0.2.2:3306/secondhand";//MYSQL数据库连接Url   
    private static String user = "root";//用户名
    private static String password = "000000";//密码

//连接数据库  

    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            String ip = "10.0.2.2";
            String ip = "10.0.2.2";
            String dbName="secondhand";
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":3306/" + dbName +"?serverTimezone=UTC&useUnicode=yes&characterEncoding=utf-8&useSSL=false",
                    "root", "000000");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

//关闭数据库


    public static void closeAll(Connection conn, PreparedStatement ps) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //关闭数据库

    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

