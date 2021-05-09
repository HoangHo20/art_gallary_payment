package DAO;

import DTO.Picture;
import DTO.Promotion;
import DTO.Staff;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class promotion_DAO {

    public static ArrayList<Promotion> selectAll(){
        Connection conn = Global_DAO.getConnection();
        ResultSet rs = null;
        Statement st = null;
        ArrayList<Promotion> res = new ArrayList();

        try{
            String sql = "select * from promotion";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                java.sql.Date dateEnd =  rs.getDate("DateEnd");
                java.sql.Date dateStart = rs.getDate("DateStart");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String DateStart =  formatter.format(new java.util.Date(dateStart.getTime()));
                String DateEnd = formatter.format(new java.util.Date(dateEnd.getTime()));
                res.add(new Promotion(String.valueOf(rs.getInt("ID")),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        DateStart,
                        DateEnd,
                        rs.getDouble("Discount_amount"),
                        rs.getInt("Discount_percentage")));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    public static Promotion selectOne(String ID){
        Connection conn = Global_DAO.getConnection();
        ResultSet rs = null;
        Statement st = null;

        try{
            String sql = "select * from promotion where promotion.ID = " + ID;
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                java.sql.Date dateEnd =  rs.getDate("DateEnd");
                java.sql.Date dateStart = rs.getDate("DateStart");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String DateStart =  formatter.format(new java.util.Date(dateStart.getTime()));
                String DateEnd = formatter.format(new java.util.Date(dateStart.getTime()));
                return new Promotion(String.valueOf(rs.getInt("ID")),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        DateStart,
                        DateEnd,
                        rs.getDouble("Discount_amount"),
                        rs.getInt("Discount_percentage"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static int insert(Promotion promotion){
        String sql = "INSERT INTO promotion (Name, Description, DateStart, DateEnd, Discount_amount, Discount_percentage, Status) " + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        Connection conn = Global_DAO.getConnection();
        PreparedStatement preparedStatement = null;

        int rowAffected = 0;

        try{
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, promotion.getName());
            preparedStatement.setString(2, promotion.getDescription());
            preparedStatement.setDate(3, new Date(promotion.getDateStart().getTime()));
            preparedStatement.setDate(4, new Date(promotion.getDateEnd().getTime()));
            preparedStatement.setDouble(5, promotion.getDiscount_amount());
            preparedStatement.setInt(6, promotion.getDiscount_percentage());
            preparedStatement.setInt(7, promotion.getCondition());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while(rs.next()){
                rowAffected = rs.getInt(1);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return rowAffected;
    }

    public static int update(String ID, Promotion promotion){
        String sqlUpdate = "UPDATE promotion "
                + "SET Name = ? "
                + ", Description = ? "
                + ", DateStart = ? "
                + ", DateEnd = ? "
                + ", Discount_amount = ? "
                + ", Discount_percentage = ? "
                + ", Status = ? "
                + "WHERE ID = ?";

        int rowAffected = 0;
        PreparedStatement preparedStatement = null;

        Connection conn = Global_DAO.getConnection();

        try{
            preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, promotion.getName());
            preparedStatement.setString(2, promotion.getDescription());
            preparedStatement.setDate(3, new Date(promotion.getDateStart().getTime()));
            preparedStatement.setDate(4, new Date(promotion.getDateEnd().getTime()));
            preparedStatement.setDouble(5, promotion.getDiscount_amount());
            preparedStatement.setInt(6, promotion.getDiscount_percentage());
            preparedStatement.setInt(7, promotion.getCondition());
            preparedStatement.setInt(8, Integer.parseInt(ID));

            rowAffected = preparedStatement.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return rowAffected;
    }

    public static int delete(int ID){
        String sql = "DELETE from promotion " + "where ID = ?";
        Connection conn = Global_DAO.getConnection();
        PreparedStatement preparedStatement = null;

        int rowAffected = 0;

        try{
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, ID);

            rowAffected = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowAffected;
    }
}
