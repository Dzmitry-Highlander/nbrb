package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

import java.time.LocalDateTime;

public class RateShortDTO {

    private int Cur_ID;
    private LocalDateTime Date;
    private double Cur_OfficialRate;

    public RateShortDTO() {
    }

    public int getCur_ID() {
        return Cur_ID;
    }

    public void setCur_ID(int cur_ID) {
        Cur_ID = cur_ID;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public double getCur_OfficialRate() {
        return Cur_OfficialRate;
    }

    public void setCur_OfficialRate(double cur_OfficialRate) {
        Cur_OfficialRate = cur_OfficialRate;
    }
}
