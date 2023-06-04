package by.it_academy.jd2.Mk_JD2_98_23.dao.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RatePeriodDTO;

import java.time.LocalDate;
import java.util.List;

public interface IRateDao extends ICRUDDao<RateCreateDTO> {
    void save(RateCreateDTO item);

    boolean checkRateDataPeriod(RatePeriodDTO item);

    boolean checkRateData(RateCreateDTO item);

    List<RateDTO> get(String curAbbreviation);

    public List<RateDTO> getPeriod(RatePeriodDTO item);

    double getAverageCurrency(LocalDate date, String curAbbreviation);
}
