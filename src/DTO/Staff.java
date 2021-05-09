package DTO;

import javax.lang.model.util.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

public class Staff {
    private String ID;
    private String name;
    private String phone;
    private String sex;
    private String dateStart;
    private String dateEnd;
    private int status;

    public Staff(String ID, String name, String phone, String sex, String dateStart, String dateEnd){
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.setStatus();
    }

    public Staff(){ }

    public String getID(){return this.ID;}

    public String getName(){return this.name;}

    public String getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }

    public int getStatus() {
        return status;
    }

    public Date getDateStart() throws ParseException {

        return new SimpleDateFormat("yyyy-MM-dd").parse(this.dateStart);
    }

    public Date getDateEnd() throws ParseException {
        if(this.dateEnd.equals("none")){
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd").parse(this.dateEnd);
    }

    public void setID(String ID){this.ID = ID;}

    public void setName(String name){this.name = name;}

    public void setDateEnd(String dateEnd){this.dateEnd = dateEnd;}

    public void setStatus(){
        if(this.dateEnd.equals("none")){
            status = 1;
        }
        else{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String local_date = dtf.format(now);

            if(dateEnd.compareTo(local_date) < 0){
                status = 0;
            }
            else{
                status = 1;
            }
        }
    }

    public Vector toVector() {
        Vector res = new Vector();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            res.add(this.getID());
            res.add(this.getName());
            res.add(this.getPhone());
            res.add(this.getSex());
            String dateStart = formatter.format(this.getDateStart());
            String dateEnd = null;
            if(this.getDateEnd() != null){
                dateEnd = formatter.format(this.getDateEnd());
            }
            res.add(dateStart);
            res.add(dateEnd);
            if(this.getStatus() == 1){
                res.add("Valid");
            }
            else{
                res.add("Invalid");
            }

            return res;
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        return new Vector();
    }
}
