package BUS;

import DAO.bill_DAO;
import DAO.staff_DAO;
import DTO.Bill;

import java.util.ArrayList;
import java.util.Vector;

public class bill_BUS {
    public static Vector<Vector> getAllBills(){
        ArrayList<Bill> list_pic = bill_DAO.selectAll();
        Vector<Vector> res = new Vector<>();

        for(Bill b: list_pic){
            Vector vector = b.toVector();
            if(!vector.isEmpty()){
                res.add(vector);
            }
        }

        return res;
    }

    public static Bill getBillByID(int id) {
        return  bill_DAO.selectOne(id);
    }

    public static int insertBill(Bill bill) {
        int count = 0;

        count += bill_DAO.insert(bill);

        return count;
    }
}
