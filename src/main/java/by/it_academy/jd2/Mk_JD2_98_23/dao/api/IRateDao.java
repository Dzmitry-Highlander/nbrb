package by.it_academy.jd2.Mk_JD2_98_23.dao.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;

import java.time.LocalDate;

public interface IRateDao extends ICRUDDao<RateCreateDTO> {
    void save(RateCreateDTO item);

    boolean checkRateData(RateCreateDTO item);
}
