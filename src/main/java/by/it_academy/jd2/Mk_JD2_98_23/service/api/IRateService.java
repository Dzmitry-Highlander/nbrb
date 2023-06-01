package by.it_academy.jd2.Mk_JD2_98_23.service.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;

import java.time.LocalDate;
import java.util.List;

public interface IRateService  extends ICRUDService<RateCreateDTO, RateCreateDTO>{
    void upload(RateCreateDTO item);

    boolean checkRateDataPeriod(String curAbbreviation, LocalDate dateStart, LocalDate dateEnd);

    boolean checkRateData(RateCreateDTO item);

    boolean dateValidate(String item);

    List<RateCreateDTO> getPeriod(LocalDate dateStart, LocalDate dateEnd);
}
