package by.it_academy.jd2.Mk_JD2_98_23.service.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyDTO;

public interface ICurrencyService extends ICRUDService<CurrencyDTO, CurrencyCreateDTO> {
    void uploadData (CurrencyCreateDTO item);
}
