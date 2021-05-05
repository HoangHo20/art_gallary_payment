package DTO;

import DTO.Promotion;

public class Picture {
    private String ID, barcode;
    private int price;
    private int type;
    private Promotion promotion;
    private String description;

    public Picture(){
        this.ID = this.barcode = this.description = "none";
        price = -1;
        type = -1;
        promotion = new Promotion();
    }

    public Picture(String ID, String barcode, int price, int type, Promotion promotion, String description){
        this.ID = ID;
        this.barcode = barcode;
        this.price = price;
        this.type = type;
        this.promotion = promotion;
        this.description = description;
    }

    public int getPrice() {
        return price;
    }
}
