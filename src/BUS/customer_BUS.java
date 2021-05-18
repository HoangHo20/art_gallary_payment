package BUS;

import DAO.customer_DAO;
import DAO.promotion_DAO;
import DTO.Customer;
import DTO.Promotion;

import java.beans.Customizer;
import java.util.ArrayList;
import java.util.Vector;

public class customer_BUS {

    public static Vector<Vector> getAllCustomer(){
        ArrayList<Customer> list = customer_DAO.selectAll();
        Vector<Vector> res = new Vector<>();

        for(Customer cus: list){
            res.add(cus.toVector());
        }
        return res;
    }

    public static int getDiscountFromType(int type){
        switch (type){
            case 1:
                return 5;

            default:
                return 0;
        }
    }

    public static int updateAllCustomer(ArrayList<Customer> list){
        int count = 0;
        for(Customer customer: list){
            int rowAffected = customer_DAO.update(customer);
            if(rowAffected == 0){
                count += customer_DAO.insert(customer);
            }
            count += rowAffected;
        }
        return count;
    }

    public static int deleteCustuomer(int ID){
        return customer_DAO.delete(ID);
    }

    public static Customer getCustomerByPhone(String phone) {
        if (phone != null && !phone.isEmpty()) {
            return customer_DAO.selectOneByPhone(phone);
        } else  {
            return null;
        }
    }

    public static int insertCustomer(Customer cus) {
        int count = 0;
        String phone = cus.getPhone();

        if (phone != null && !phone.isEmpty()) {
            Customer select_customer = getCustomerByPhone(cus.getPhone());

            if (select_customer == null) { //if customer didn't exist
                count += customer_DAO.insert(cus);
            }
        }

        return count;
    }
}
