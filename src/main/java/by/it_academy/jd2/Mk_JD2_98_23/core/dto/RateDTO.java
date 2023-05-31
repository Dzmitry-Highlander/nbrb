package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class RateDTO {
    @JsonProperty("Cur_ID")
    private int curID;
    @JsonProperty("Date")
    private LocalDate date;
    @JsonProperty("Cur_OfficialRate")
    private double curOfficialRate;

    public RateDTO() {
    }

    public RateDTO(int cur_ID, LocalDate date, double cur_OfficialRate) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCurOfficialRate() {
        return curOfficialRate;
    }

    public void setCurOfficialRate(double curOfficialRate) {
        this.curOfficialRate = curOfficialRate;
    }
}
