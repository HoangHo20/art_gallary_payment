package BUS;

import DAO.*;
import DTO.Picture;
import DTO.Staff;

import java.util.ArrayList;
import java.util.Vector;

public class staff_BUS {

    public static Vector<Vector> getAllStaff(){
        ArrayList<Staff> list_pic = staff_DAO.selectAll();
        Vector<Vector> res = new Vector<>();

        for(Staff pic: list_pic){
            res.add(pic.toVector());
        }

        return res;
    }

    public static void updateStaff(ArrayList<Staff> list){
        for(Staff staff: list){
            int rowAffected = staff_DAO.update(staff.getID(), staff);
            if(rowAffected == 0){
                staff_DAO.insert(staff);
            }
        }
    }

    public static int deleteStaff(String ID){
        return staff_DAO.delete(Integer.parseInt(ID));
    }
}
