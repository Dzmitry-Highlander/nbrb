package by.it_academy.jd2.Mk_JD2_98_23.service.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;

import java.time.LocalDate;

public interface IRateService extends ICRUDService<RateDTO, RateCreateDTO> {
    boolean checkPeriod(LocalDate from, LocalDate to);

    void save(RateCreateDTO item);
}
