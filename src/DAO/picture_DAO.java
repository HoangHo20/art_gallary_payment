package DAO;

import DTO.Picture;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class picture_DAO {

    public picture_DAO(){ }


    public static ArrayList<Picture> selectAll(){
        Connection conn = Global_DAO.getConnection();
        ResultSet rs = null;
        Statement st = null;
        ArrayList<Picture> res = new ArrayList();

        try{
            String sql = "select * from picture";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                res.add(new Picture(String.valueOf(rs.getInt("ID")),
                        rs.getString("Barcode"),
                        rs.getInt("Type"),
                        rs.getInt("Price"),
                        String.valueOf(rs.getInt("Promotion")),
                        rs.getString("Description")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    public static Picture selectOne(String barcode){
        Connection conn = Global_DAO.getConnection();
        ResultSet rs = null;
        Statement st = null;

        try{
            String sql = "select * from picture where picture.Barcode = " + barcode;
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                return (new Picture(String.valueOf(rs.getInt("ID")),
                        rs.getString("Barcode"),
                        rs.getInt("Type"),
                        rs.getInt("Price"),
                        String.valueOf(rs.getInt("Promotion")),
                        rs.getString("Description")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static int insert(Picture pic){
        String sql = "INSERT INTO picture (Barcode, Type, Price, Promotion, Description) " + "VALUES(?, ?, ?, ?, ?)";
        Connection conn = Global_DAO.getConnection();
        PreparedStatement preparedStatement = null;

        int rowAffected = 0;

        try{
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pic.getBarcode());
            preparedStatement.setInt(2, pic.getType());
            preparedStatement.setInt(3, pic.getPrice());
            if(pic.getPromotion().equals("-1")){
                preparedStatement.setInt(4, Types.NULL);
            }
            else{
                preparedStatement.setInt(4, Integer.parseInt(pic.getPromotion()));
            }

            preparedStatement.setString(5, pic.getDescription());

            rowAffected = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowAffected;
    }

    public static int update(String barcode, Picture pic){
        String sqlUpdate = "UPDATE picture "
                + "SET Type = ? "
                + ", Price = ? "
                + ", Promotion = ? "
                + ", Description = ? "
                + "WHERE Barcode = ?";

        int rowAffected = 0;
        PreparedStatement preparedStatement = null;

        Connection conn = Global_DAO.getConnection();

        try{
            preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setInt(1, pic.getType());
            preparedStatement.setInt(2, pic.getPrice());
            preparedStatement.setInt(3, Integer.parseInt(pic.getPromotion()));
            preparedStatement.setString(4, pic.getDescription());
            preparedStatement.setString(5, barcode);

            rowAffected = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowAffected;
    }

    public static int delete(String barcode){
        String sql = "DELETE from picture " + "where Barcode = ?";
        Connection conn = Global_DAO.getConnection();
        PreparedStatement preparedStatement = null;

        int rowAffected = 0;

        try{
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, barcode);

            rowAffected = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowAffected;
    }
}
