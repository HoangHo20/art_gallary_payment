package DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Staff(String ID, String name, String phone, String sex, String dateStart, String dateEnd, int status){
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.status = status;
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

    public void setStatus(int status){this.status = status;}

    public Vector toVector() {
        Vector res = new Vector();
        try{
            res.add(this.getID());
            res.add(this.getName());
            res.add(this.getPhone());
            res.add(this.getSex());
            res.add(new String(String.valueOf(this.getDateStart())));
            res.add(new String(String.valueOf(this.getDateEnd())));
            res.add(this.getStatus());
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        finally {
            return res;
        }

    }
}
