package DTO;

import java.util.Vector;

public class Customer {
    private String name, phone;
    private int type, discount, ID;

    public Customer(){
        this.name = this.phone = "none";
        type = ID = -1;
    }

    public Customer(int ID, String name, String phone, int type){
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.getDiscountFromType();
    }

    public int getID(){return this.ID;}

    public String getName(){return this.name;}

    public String getPhone(){return this.phone;}

    public int getType() {
        return type;
    }

    public int getDiscount() {
        return discount;
    }

    public void setType(int type){
        this.type = type;
        this.getDiscountFromType();
    }

    private void getDiscountFromType(){
        switch (this.type){
            case 1:
                // set value of discount
                break;

            case 2:

                break;

            default:
                discount = 0;
                break;
        }
    }

    public Vector toVector(){
        Vector res = new Vector();
        res.add(this.ID);
        res.add(this.name);
        res.add(this.phone);
        res.add(this.type);
        res.add(this.discount);
        return res;
    }
}
