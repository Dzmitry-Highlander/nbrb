package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RateDTO {
    private int id;
    @JsonProperty("Cur_ID")
    private int cur_ID;
    @JsonProperty("Date")
    private LocalDateTime date;
    @JsonProperty("Cur_Abbreviation")
    private String cur_Abbreviation;
    @JsonProperty("Cur_Scale")
    private int cur_Scale;
    @JsonProperty("Cur_Name")
    private String cur_Name;
    @JsonProperty("Cur_OfficialRate")
    private double cur_OfficialRate;

    public RateDTO() {
    }

    public RateDTO(int id, int cur_ID, LocalDateTime date, String cur_Abbreviation, int cur_Scale, String cur_Name,
                   double cur_OfficialRate) {
        this.id = id;
        this.cur_ID = cur_ID;
        this.date = date;
        this.cur_Abbreviation = cur_Abbreviation;
        this.cur_Scale = cur_Scale;
        this.cur_Name = cur_Name;
        this.cur_OfficialRate = cur_OfficialRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCur_ID() {
        return cur_ID;
    }

    public void setCur_ID(int cur_ID) {
        this.cur_ID = cur_ID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCur_Abbreviation() {
        return cur_Abbreviation;
    }

    public void setCur_Abbreviation(String cur_Abbreviation) {
        this.cur_Abbreviation = cur_Abbreviation;
    }

    public int getCur_Scale() {
        return cur_Scale;
    }

    public void setCur_Scale(int cur_Scale) {
        this.cur_Scale = cur_Scale;
    }

    public String getCur_Name() {
        return cur_Name;
    }

    public void setCur_Name(String cur_Name) {
        this.cur_Name = cur_Name;
    }

    public double getCur_OfficialRate() {
        return cur_OfficialRate;
    }

    public void setCur_OfficialRate(double cur_OfficialRate) {
        this.cur_OfficialRate = cur_OfficialRate;
    }
}
