package DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Promotion {
    private String ID, name, description, dateStart, dateEnd;
    private double discount_amount;
    private int discount_percentage;
    private int condition;              // true: available; false: not available

    public Promotion(){
        this.ID = this.name = this.description = this.dateStart = this.dateEnd = "none";
        this.discount_amount = -1.0;
        this.discount_percentage = -1;
        this.condition = 0;
    }

    public Promotion(String ID, String name, String description, String dateStart, String dateEnd, double discount_amount, int discount_percentage, int condition){
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

    public Date getDateStart() throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(this.dateStart);
    }

    public Date getDateEnd() throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(this.dateEnd);
    }

    public int getDiscount_percentage() {
        return discount_percentage;
    }

    public double getDiscount_amount() {
        return discount_amount;
    }

    public int getCondition(){
        return condition;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Vector toVector(){
        Vector res = new Vector();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            res.add(this.getID());
            res.add(this.getName());
            res.add(this.getDescription());
            String dateStart = formatter.format(this.getDateStart());
            String dateEnd = formatter.format(this.getDateEnd());
            res.add(dateStart);
            res.add(dateEnd);
            res.add(this.getDiscount_amount());
            res.add(this.getDiscount_percentage());
            res.add(this.getCondition());

            return res;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Vector();
    }

}
