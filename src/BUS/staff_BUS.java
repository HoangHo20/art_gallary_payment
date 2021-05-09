package BUS;

import DAO.*;
import DTO.Picture;
import DTO.Promotion;
import DTO.Staff;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

public class staff_BUS {

    public static Vector<Vector> getAllStaff(){
        ArrayList<Staff> list_pic = staff_DAO.selectAll();
        Vector<Vector> res = new Vector<>();

        for(Staff pic: list_pic){
            Vector vector = pic.toVector();
            if(!vector.isEmpty()){
                res.add(vector);
            }
        }

        return res;
    }

    public static int updateStaff(ArrayList<Staff> list){
        int count = 0;
        for(Staff staff: list){
            int rowAffected = staff_DAO.update(staff.getID(), staff);
            if(rowAffected == 0){
                count += staff_DAO.insert(staff);
            }
            count += rowAffected;
        }
        return count;
    }

    public static int deleteStaff(String ID){
        return staff_DAO.delete(Integer.parseInt(ID));
    }

    public static boolean checkStatus(String dateEnd){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String local_date = dtf.format(now);

        if(dateEnd.compareTo(local_date) < 0){
            return false;
        }
        else{
            return true;
        }
    }
}
