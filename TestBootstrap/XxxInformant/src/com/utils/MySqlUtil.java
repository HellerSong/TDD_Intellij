package com.utils;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

/**
 * <p>Summary: Basic tool for MySQL operation.</p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class MySqlUtil {
    public static String connUrl;
    private static String driver;
    private static String user;
    private static String password;

    static {
        try {
            String dbConfigFile = MySqlUtil.class.getClassLoader().getResource("dbconfig.properties").getPath();
            Properties properties = new Properties();
            properties.load(new FileReader(dbConfigFile));

            //// Read properties file's data
            driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String server = properties.getProperty("server");
            String port = properties.getProperty("port");
            String database = properties.getProperty("database");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

            //// Join together for the connection URL
            connUrl = url + "//" + server + ":" + port + "/" + database;
            connUrl += "?useUnicode=true&characterEncoding=UTF-8";
        } catch (Exception e) {
            DevLog.write(e);
        }
    }

    public static Connection getConnection() {
        Connection conn = null; // "jdbc:mysql://127.0.0.1/

        try {
            Class.forName(driver);

            DevLog.write(connUrl);

            conn = DriverManager.getConnection(connUrl, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

        return conn;
    }

    public static void close(Statement pstmt, ResultSet rs, Connection conn) {
        try {
            if (pstmt != null)
                pstmt.close();
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void beginTransaction(Connection conn) {
        try {
            if (conn != null) {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commitTransaction(Connection conn) {
        try {
            if (conn != null) {
                if (!conn.getAutoCommit()) {
                    conn.commit();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollbackTransaction(Connection conn) {
        try {
            if (conn != null) {
                if (!conn.getAutoCommit()) {
                    conn.rollback();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void resetConnection(Connection conn) {
        try {
            if (conn != null) {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                } else {
                    conn.setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
