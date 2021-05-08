package DAO;

import DTO.Picture;
import DTO.Promotion;
import DTO.Staff;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class staff_DAO {
    public static ArrayList<Staff> selectAll(){
        Connection conn = Global_DAO.getConnection();
        ResultSet rs = null;
        Statement st = null;
        ArrayList<Staff> res = new ArrayList();

        try{
            String sql = "select * from staff";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                java.sql.Date dateEnd =  rs.getDate("DateEnd");
                java.sql.Date dateStart = rs.getDate("DateStart");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String DateStart =  formatter.format(new java.util.Date(dateStart.getTime()));
                String DateEnd = "";

                if(dateEnd == null){
                    DateEnd = "none";
                }
                else{
                    DateEnd = formatter.format(new java.util.Date(dateEnd.getTime()));
                }
                res.add(new Staff(String.valueOf(rs.getInt("ID")),
                        rs.getString("Name"),
                        rs.getString("Phone"),
                        rs.getString("Sex"),
                        DateStart,
                        DateEnd,
                        rs.getInt("Status")));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    public static Staff selectOne(String ID){
        Connection conn = Global_DAO.getConnection();
        ResultSet rs = null;
        Statement st = null;

        try{
            String sql = "select * from staff where staff.ID = " + ID;
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                java.sql.Date dateEnd =  rs.getDate("DateEnd");
                java.sql.Date dateStart = rs.getDate("DateStart");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String DateStart =  formatter.format(new java.util.Date(dateStart.getTime()));
                String DateEnd = "";

                if(dateEnd == null){
                    DateEnd = "none";
                }
                else{
                    DateEnd = formatter.format(new java.util.Date(dateEnd.getTime()));
                }
                return (new Staff(String.valueOf(rs.getInt("ID")),
                        rs.getString("Name"),
                        rs.getString("Phone"),
                        rs.getString("Sex"),
                        DateStart,
                        DateEnd,
                        rs.getInt("Status")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static int insert(Staff staff){
        String sql = "INSERT INTO staff (Name, Phone, Sex, DateStart, DateEnd, Status) " + "VALUES(?, ?, ?, ?, ?, ?)";
        Connection conn = Global_DAO.getConnection();
        PreparedStatement preparedStatement = null;

        int rowAffected = 0;

        try{
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getPhone());
            preparedStatement.setString(3, staff.getSex());
            preparedStatement.setDate(4, new Date(staff.getDateStart().getTime()));
            if(staff.getDateEnd() == null){
                preparedStatement.setNull(5, Types.DATE);
            }
            else{
                preparedStatement.setDate(5, new Date(staff.getDateEnd().getTime()));
            }
            preparedStatement.setInt(6, staff.getStatus());

            rowAffected = preparedStatement.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return rowAffected;
    }

    public static int update(String ID, Staff staff){
        String sqlUpdate = "UPDATE staff "
                + "SET Name = ? "
                + ", Phone = ? "
                + ", Sex = ? "
                + ", DateStart = ? "
                + ", DateEnd = ? "
                + ", Status = ? "
                + "WHERE ID = ?";

        int rowAffected = 0;
        PreparedStatement preparedStatement = null;

        Connection conn = Global_DAO.getConnection();

        try{
            preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getPhone());
            preparedStatement.setString(3, staff.getSex());
            preparedStatement.setDate(4, new Date(staff.getDateStart().getTime()));
            if(staff.getDateEnd() == null){
                preparedStatement.setNull(5, Types.DATE);
            }
            else{
                preparedStatement.setDate(5, new Date(staff.getDateEnd().getTime()));
            }
            preparedStatement.setDouble(6, staff.getStatus());
            preparedStatement.setInt(7, Integer.parseInt(staff.getID()));

            rowAffected = preparedStatement.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return rowAffected;
    }

    public static int delete(int ID){
        String sql = "DELETE from staff " + "where ID = ?";
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
