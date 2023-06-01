package by.it_academy.jd2.Mk_JD2_98_23.dao.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;

import java.time.LocalDate;
import java.util.List;

public interface IRateDao extends ICRUDDao<RateCreateDTO> {
    void save(RateCreateDTO item);

    boolean checkRateDataPeriod(String curAbbreviation, LocalDate dateStart, LocalDate dateEnd);

    boolean checkRateData(RateCreateDTO item);

    List<RateDTO> get(String curAbbreviation);

    List<RateCreateDTO> getPeriod(LocalDate dateStart, LocalDate dateEnd);
}
