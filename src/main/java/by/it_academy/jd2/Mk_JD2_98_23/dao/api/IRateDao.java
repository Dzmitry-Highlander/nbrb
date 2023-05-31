package by.it_academy.jd2.Mk_JD2_98_23.dao.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;

import java.time.LocalDate;

public interface IRateDao extends ICRUDDao<RateDTO> {
    boolean checkPeriod(LocalDate from, LocalDate to);

    void save(RateCreateDTO item);
}
