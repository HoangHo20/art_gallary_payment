package BUS;

import DAO.promotion_DAO;
import DTO.Promotion;

import java.util.ArrayList;
import java.util.Vector;

public class promotion_BUS {
    public static Vector<Vector> getAllPromotion(){
        ArrayList<Promotion> list_pic = promotion_DAO.selectAll();
        Vector<Vector> res = new Vector<>();

        for(Promotion pic: list_pic){
            Vector vector = pic.toVector();
            if(!vector.isEmpty()){
                res.add(vector);
            }
        }

        return res;
    }

    public static int updateStaff(ArrayList<Promotion> list){
        int count = 0;
        for(Promotion staff: list){
            int rowAffected = promotion_DAO.update(staff.getID(), staff);
            if(rowAffected == 0){
                count += promotion_DAO.insert(staff);
            }
            count += rowAffected;
        }
        return count;
    }

    public static int deleteStaff(String ID){
        return promotion_DAO.delete(Integer.parseInt(ID));
    }
}
