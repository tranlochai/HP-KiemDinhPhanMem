package DAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSql {
    private static final String url = "jdbc:mariadb://localhost:3307/myApp";
    private static final String userName = "root";
    private static final String password = "123";

    public static Connection getConnect() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(url, userName, password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi kết nối CSDL", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static void rollBack(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static void setAutocommit(Connection connection, boolean b) {
        try {
            connection.setAutoCommit(b);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static void connectClose(Connection connection) {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
