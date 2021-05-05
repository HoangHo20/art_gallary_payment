package DTO;

public class Promotion {
    private String ID, name, description, dateStart, dateEnd;
    private double discount_amount;
    private int discount_percentage;
    private boolean condition;              // true: available; false: not available

    public Promotion(){
        this.ID = this.name = this.description = this.dateStart = this.dateEnd = "none";
        this.discount_amount = -1.0;
        this.discount_percentage = -1;
        this.condition = false;
    }

    public Promotion(String ID, String name, String description, String dateStart, String dateEnd, double discount_amount, int discount_percentage, boolean condition){
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.discount_amount = discount_amount;
        this.discount_percentage = discount_percentage;
        this.condition = condition;
    }

    public String getID() {
        return ID;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public int getDiscount_percentage() {
        return discount_percentage;
    }

    public double getDiscount_amount() {
        return discount_amount;
    }

    public boolean getCondition(){
        return condition;
    }
}
