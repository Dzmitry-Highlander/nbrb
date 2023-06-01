package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RateDTO {

    @JsonProperty("Date")
    private LocalDate date;
    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;
    @JsonProperty("Cur_OfficialRate")
    private double curOfficialRate;

    public RateDTO() {
    }

    public RateDTO(LocalDate date, String curAbbreviation, double curOfficialRate) {
        this.date = date;
        this.curAbbreviation = curAbbreviation;
        this.curOfficialRate = curOfficialRate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCurAbbreviation() {
        return curAbbreviation;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    public double getCurOfficialRate() {
        return curOfficialRate;
    }

    public void setCurOfficialRate(double curOfficialRate) {
        this.curOfficialRate = curOfficialRate;
    }
}
