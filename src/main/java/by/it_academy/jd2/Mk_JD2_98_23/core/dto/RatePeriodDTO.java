package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

import java.time.LocalDate;

public class RatePeriodDTO {

    private String curAbbreviation;
    private LocalDate startDate;
    private LocalDate endDate;

    public RatePeriodDTO() {
    }

    public RatePeriodDTO(String curAbbreviation, LocalDate startDate, LocalDate endDate) {
        this.curAbbreviation = curAbbreviation;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCurAbbreviation() {
        return curAbbreviation;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
