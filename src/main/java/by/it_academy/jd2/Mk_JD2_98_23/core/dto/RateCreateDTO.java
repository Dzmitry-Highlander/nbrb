package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RateCreateDTO {

    @JsonProperty("Cur_ID")
    private int curId;

    @JsonProperty("Date")
    private LocalDateTime date;

    @JsonProperty("Cur_OfficialRate")
    private double curOfficialRate;


    public RateCreateDTO() {
    }

    public RateCreateDTO(int curId, LocalDateTime date, double curOfficialRate) {
        this.curId = curId;
        this.date = date;
        this.curOfficialRate = curOfficialRate;
    }

    public int getCurId() {
        return curId;
    }

    public void setCurId(int curId) {
        this.curId = curId;
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
}
