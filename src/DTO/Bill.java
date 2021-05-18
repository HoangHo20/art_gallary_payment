package DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Bill {
    int ID, total;
    int staff_ID, customer_ID;
    String date;

    public Bill(int total, int staff_ID, int customer_ID, Date date) {
        this.total = total;
        this.staff_ID = staff_ID;
        this.customer_ID = customer_ID;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.date = formatter.format(date);
    }

    public Bill(int id, int total, int staff_ID, int customer_ID, String date) {
        this.ID = id;
        this.total = total;
        this.staff_ID = staff_ID;
        this.customer_ID = customer_ID;
        this.date = date;
    }

    public int getTotal() {
        return this.total;
    }

    public int getStaff_ID() {
        return this.staff_ID;
    }

    public int getCustomer_ID() {
        return this.customer_ID;
    }

    public Date getDate() throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(this.date);
    }

    public String getDateString() {
        return this.date;
    }

    public int getID() {
        return this.ID;
    }

    public Vector toVector() {
        Vector res = new Vector();

        res.add(this.ID);
        res.add(this.getTotal());
        res.add(this.getStaff_ID());
        res.add(this.getCustomer_ID());
        res.add(this.date);

        return res;
    }
}
