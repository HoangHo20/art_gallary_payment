package BUS;

import DAO.picture_DAO;
import DAO.promotion_DAO;
import DTO.Picture;
import DTO.Promotion;

import java.util.ArrayList;
import java.util.Vector;

public class picture_BUS {

    public static Vector<Vector> getAllPicture(){
        ArrayList<Picture> list_pic = picture_DAO.selectAll();
        Vector<Vector> res = new Vector<>();

        for(Picture pic: list_pic){
            res.add(pic.toVector());
        }

        return res;
    }

    public static int deletePicture(String barcode){
        return picture_DAO.delete(barcode);
    }

    public static int updatePicture(ArrayList<Picture> list){
        int count = 0;
        for(Picture picture: list){
            int rowAffected = picture_DAO.update(picture.getBarcode(), picture);
            if(rowAffected == 0){
                count += picture_DAO.insert(picture);
            }
            count += rowAffected;
        }
        return count;
    }
}
