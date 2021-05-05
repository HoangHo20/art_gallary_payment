package DTO;

public class Staff {
    private String ID;
    private String name;
    private String phone;
    private String sex;
    private String dateStart;
    private String dateEnd;
    private boolean status;             // true: Còn làm việc; false: nghỉ việc

    public Staff(String ID, String name, String phone, String sex, String dateStart, String dateEnd, boolean status){
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

    public String getDateStart(){return this.dateStart;}

    public String getDateEnd(){return this.dateEnd;}

    public void setID(String ID){this.ID = ID;}

    public void setName(String name){this.name = name;}

    public void setDateEnd(String dateEnd){this.dateEnd = dateEnd;}

    public void setStatus(boolean status){this.status = status;}
}
