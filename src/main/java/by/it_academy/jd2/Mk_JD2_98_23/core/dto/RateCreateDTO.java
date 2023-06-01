package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RateCreateDTO {
    @JsonProperty("Cur_ID")
    private int curID;
    @JsonProperty("Date")
    private LocalDateTime date;
    @JsonProperty("Cur_OfficialRate")
    private double curOfficialRate;

    public RateCreateDTO() {
    }

    public RateCreateDTO(int cur_ID, LocalDateTime date, double cur_OfficialRate) {
        curID = cur_ID;
        this.date = date;
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

    public double getCurOfficialRate() {
        return curOfficialRate;
    }

    public void setCurOfficialRate(double curOfficialRate) {
        this.curOfficialRate = curOfficialRate;
    }

    @Override
    public String toString() {
        return "{ \n" +
                "Cur_ID: " + getCurID() + "\n" +
                "Date: " + getDate() + "\n" +
                "Cur_OfficialRate: " + getCurOfficialRate() + "\n" +
                "}";
    }
}
