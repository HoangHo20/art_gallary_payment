package DAO;

import DTO.Bill;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class bill_DAO {

    public static ArrayList<Bill> selectAll(){
        Connection conn = Global_DAO.getConnection();
        ResultSet rs = null;
        Statement st = null;
        ArrayList<Bill> res = new ArrayList();

        try{
            String sql = "select * from bill";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                java.sql.Date date =  rs.getDate("Date");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String date_string =  formatter.format(new java.util.Date(date.getTime()));
                
                res.add(new Bill(rs.getInt("ID"),
                        rs.getInt("Total"),
                        rs.getInt("Staff_ID"),
                        rs.getInt("Customer_ID"),
                        date_string
                        ));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    public static Bill selectOne(int ID){
        Connection conn = Global_DAO.getConnection();
        ResultSet rs = null;
        Statement st = null;

        try{
            String sql = "select * from bill where bill.ID = " + ID;
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                java.sql.Date date =  rs.getDate("Date");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String date_string =  formatter.format(new java.util.Date(date.getTime()));

                return new Bill(rs.getInt("ID"),
                        rs.getInt("Total"),
                        rs.getInt("Staff_ID"),
                        rs.getInt("Customer_ID"),
                        date_string);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static int insert(Bill bill){
        String sql = "INSERT INTO bill (Total, Staff_ID, Customer_ID, Date) " + "VALUES(?, ?, ?, ?)";
        Connection conn = Global_DAO.getConnection();
        PreparedStatement preparedStatement = null;

        int rowAffected = 0;

        try{
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, bill.getTotal());
            preparedStatement.setInt(2, bill.getStaff_ID());
            preparedStatement.setInt(3, bill.getCustomer_ID());
            preparedStatement.setDate(4, new Date(bill.getDate().getTime()));

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
}
