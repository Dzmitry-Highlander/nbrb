package by.it_academy.jd2.Mk_JD2_98_23.service.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;

import java.time.LocalDate;
import java.util.List;

public interface IRateService  extends ICRUDService<RateCreateDTO, RateCreateDTO>{
    void upload(RateCreateDTO item);

    boolean checkRateDataPeriod(String curAbbreviation, LocalDate dateStart, LocalDate dateEnd);

    boolean checkRateData(RateCreateDTO item);

    boolean dateValidate(String item);
    boolean dateValidate(LocalDate date);

    boolean currencyValidate(String item);

    List<RateDTO> get(String curAbbreviation);

    double getAverageCurrency(String currency, String year, String month);

    boolean monthValidate(String month);

    boolean yearValidate(String year);
}
