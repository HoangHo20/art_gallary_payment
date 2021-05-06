package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Global_DAO {

    public static Connection getConnection(){
        Connection conn = null;

        try{
            String dbURL = "jdbc:mysql://localhost/artGallery";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            return conn;
        }
    }
}
