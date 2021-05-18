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

    public static Vector<Vector> getAvailablePictures() {
        ArrayList<Picture> list_pic = picture_DAO.selectAvailable();
        Vector<Vector> res = new Vector<>();

        for(Picture pic: list_pic){
            res.add(pic.toVector());
        }

        return res;
    }

    public static Vector<Vector> getPicturesByValues(String barcode, String description, int price) {
        if (barcode == null || barcode.isEmpty())  {
            barcode = null;
        }

        if (description == null || description.isEmpty()) {
            description = null;
        }

        ArrayList<Picture> list_pic = picture_DAO.selectByValues(barcode, description, price);
        Vector<Vector> res = new Vector<>();

        for(Picture pic: list_pic){
            res.add(pic.toVector());
        }

        return res;
    }

    public static Picture getPictureByBarcode(String barcode) {
        return picture_DAO.selectOne(barcode);
    }

    public static int update(String barcode, int bill_id) {
        return picture_DAO.update(barcode, bill_id);
    }

    public static Vector<Vector> getPicturesByBillID(int bill_id){
        ArrayList<Picture> list_pic = picture_DAO.selectByBillID(bill_id);
        Vector<Vector> res = new Vector<>();

        for(Picture pic: list_pic){
            res.add(pic.toVector());
        }

        return res;
    }
}
