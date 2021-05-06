package DTO;

import DTO.Promotion;

import java.util.Vector;

public class Picture {
    private String ID, barcode;
    private int price;
    private int type;
    private String promotion;
    private String description;

    public Picture(){
        this.ID = this.barcode = this.description = "none";
        price = -1;
        type = -1;
        this.promotion = "-1";
    }

    public Picture(String ID, String barcode, int price, int type, String promotion, String description){
        this.ID = ID;
        this.barcode = barcode;
        this.price = price;
        this.type = type;
        this.promotion = promotion;
        this.description = description;
    }

    public Vector toVector(){
        Vector res = new Vector();
        res.add(ID);
        res.add(barcode);
        res.add(price);
        res.add(type);
        res.add(promotion);
        res.add(description);
        return res;
    }

    public int getPrice() {
        return price;
    }

    public int getType() {
        return type;
    }

    public String getPromotion() {
        return promotion;
    }

    public String getDescription(){
        return this.description;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getID() {
        return ID;
    }
}
