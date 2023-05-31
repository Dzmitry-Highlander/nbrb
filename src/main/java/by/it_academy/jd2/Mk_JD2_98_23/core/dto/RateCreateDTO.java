package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RateCreateDTO {
    @JsonProperty("Cur_ID")
    private int curID;
    @JsonProperty("Date")
    private LocalDateTime date;
    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;
    @JsonProperty("Cur_Scale")
    private int curScale;
    @JsonProperty("Cur_Name")
    private String curName;
    @JsonProperty("Cur_OfficialRate")
    private double curOfficialRate;

    public RateCreateDTO() {
    }

    public RateCreateDTO(int cur_ID, LocalDateTime date, String cur_Abbreviation, int cur_Scale, String cur_Name,
                         double cur_OfficialRate) {
        curID = cur_ID;
        this.date = date;
        curAbbreviation = cur_Abbreviation;
        curScale = cur_Scale;
        curName = cur_Name;
        curOfficialRate = cur_OfficialRate;
    }

    public int getCurID() {
        return curID;
    }

    public void setCurID(int curID) {
        this.curID = curID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCurAbbreviation() {
        return curAbbreviation;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    public int getCurScale() {
        return curScale;
    }

    public void setCurScale(int curScale) {
        this.curScale = curScale;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public double getCurOfficialRate() {
        return curOfficialRate;
    }

    public void setCurOfficialRate(double curOfficialRate) {
        this.curOfficialRate = curOfficialRate;
    }
}
