package DAO;

import DTO.Customer;
import DTO.Picture;

import java.sql.*;
import java.util.ArrayList;

public class customer_DAO {
    public static ArrayList<Customer> selectAll(){
        Connection conn = Global_DAO.getConnection();
        ResultSet rs = null;
        Statement st = null;
        ArrayList<Customer> res = new ArrayList();

        try{
            String sql = "select * from customer";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                res.add(new Customer(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Phone"),
                        rs.getInt("Type")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    public static Customer selectOne(int ID){
        Connection conn = Global_DAO.getConnection();
        ResultSet rs = null;
        PreparedStatement st = null;

        try{
            String sql = "select * from customer where customer.ID = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, ID);
            rs = st.executeQuery();
            while(rs.next()){
                return (new Customer(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Phone"),
                        rs.getInt("Type")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static int insert(Customer customer){
        String sql = "INSERT INTO customer (Name, Phone, Type, Discount) " + "VALUES(?, ?, ?, ?)";
        Connection conn = Global_DAO.getConnection();
        PreparedStatement preparedStatement = null;

        int rowAffected = 0;

        try{
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setInt(3, customer.getType());
            preparedStatement.setInt(4, customer.getDiscount());

            rowAffected = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowAffected;
    }

    public static int update(Customer customer){
        String sqlUpdate = "UPDATE customer "
                + "SET NAME = ? "
                + ", Phone = ? "
                + ", Type = ? "
                + ", Discount = ? "
                + "WHERE ID = ?";

        int rowAffected = 0;
        PreparedStatement preparedStatement = null;

        Connection conn = Global_DAO.getConnection();

        try{
            preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setInt(3, customer.getType());
            preparedStatement.setInt(4, customer.getDiscount());
            preparedStatement.setInt(5, customer.getID());

            rowAffected = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowAffected;
    }

    public static int delete(int ID){
        String sql = "DELETE from customer " + "where ID = ?";
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
