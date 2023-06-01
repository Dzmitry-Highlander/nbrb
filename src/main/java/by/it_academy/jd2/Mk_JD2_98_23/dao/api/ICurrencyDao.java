package by.it_academy.jd2.Mk_JD2_98_23.dao.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyDTO;

public interface ICurrencyDao extends ICRUDDao <CurrencyDTO>{
    void uploadData(CurrencyCreateDTO item);

    int getCount();

    int getCurID(String curAbbreviation);

    boolean currencyValidate(String item);
}
