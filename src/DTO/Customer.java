package DTO;

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
}
