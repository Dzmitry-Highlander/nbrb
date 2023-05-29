package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

import java.util.Date;

public class RateDTO {
    private int id;
    private int Cur_ID;
    private Date Date;
    private String Cur_Abbreviation;
    private int Cur_Scale;
    private String Cur_Name;
    private double Cur_OfficialRate;

    public RateDTO() {
    }

    public RateDTO(int id, int cur_ID, java.util.Date date, String cur_Abbreviation, int cur_Scale, String cur_Name,
                   double cur_OfficialRate) {
        this.id = id;
        Cur_ID = cur_ID;
        Date = date;
        Cur_Abbreviation = cur_Abbreviation;
        Cur_Scale = cur_Scale;
        Cur_Name = cur_Name;
        Cur_OfficialRate = cur_OfficialRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCur_ID() {
        return Cur_ID;
    }

    public void setCur_ID(int cur_ID) {
        Cur_ID = cur_ID;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getCur_Abbreviation() {
        return Cur_Abbreviation;
    }

    public void setCur_Abbreviation(String cur_Abbreviation) {
        Cur_Abbreviation = cur_Abbreviation;
    }

    public int getCur_Scale() {
        return Cur_Scale;
    }

    public void setCur_Scale(int cur_Scale) {
        Cur_Scale = cur_Scale;
    }

    public String getCur_Name() {
        return Cur_Name;
    }

    public void setCur_Name(String cur_Name) {
        Cur_Name = cur_Name;
    }

    public double getCur_OfficialRate() {
        return Cur_OfficialRate;
    }

    public void setCur_OfficialRate(double cur_OfficialRate) {
        Cur_OfficialRate = cur_OfficialRate;
    }
}
