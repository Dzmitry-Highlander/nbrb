package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

import java.time.LocalDateTime;

public class RateShortDTO {

    private int id;
    private int Cur_ID;
    private LocalDateTime Date;
    private double Cur_OfficialRate;

    public RateShortDTO() {
    }

    public RateShortDTO(int id, int cur_ID, LocalDateTime date, double cur_OfficialRate) {
        this.id = id;
        Cur_ID = cur_ID;
        Date = date;
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
