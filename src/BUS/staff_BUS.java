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
}
