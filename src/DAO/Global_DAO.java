package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Global_DAO {
    private static Connection conn = null;

    public static Connection getConnection(){
        if (conn != null) {
            return conn;
        } else {
            connect_to_database();
            return conn;
        }
    }

    private static void connect_to_database() {
        try{
            String dbURL = "jdbc:mysql://localhost/artGallery";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
