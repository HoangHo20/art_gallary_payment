package BUS;

import DAO.customer_DAO;
import DTO.Customer;

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
}
