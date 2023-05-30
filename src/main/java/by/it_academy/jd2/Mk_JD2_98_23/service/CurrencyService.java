package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.ICurrencyDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.ICurrencyService;

import java.util.List;

public class CurrencyService implements ICurrencyService {

    private final ICurrencyDao currencyDao;

    public CurrencyService(ICurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }


    @Override
    public List get() {
        return currencyDao.get();
    }

    @Override
    public CurrencyDTO get(int id) {
        return currencyDao.get(id);
    }

    @Override
    public CurrencyDTO save(CurrencyCreateDTO item) {
        CurrencyDTO dto = new CurrencyDTO();
        dto.setCur_ID(item.getCur_ID());
        dto.setCur_ParentID(item.getCur_ParentID());
        dto.setCur_Code(item.getCur_Code());
        dto.setCur_Abbreviation(item.getCur_Abbreviation());
        dto.setCur_Name(item.getCur_Name());
        dto.setCur_Name_Eng(item.getCur_Name_Eng());
        dto.setCur_QuotName(item.getCur_QuotName());
        dto.setCur_QuotName_Bel(item.getCur_QuotName_Bel());
        dto.setCur_QuotName_Eng(item.getCur_QuotName_Eng());
        dto.setCur_NameMulti(item.getCur_NameMulti());
        dto.setCur_Name_BelMulti(item.getCur_Name_BelMulti());
        dto.setCur_Name_EngMulti(item.getCur_Name_EngMulti());
        dto.setCur_Scale(item.getCur_Scale());
        dto.setCur_Periodicity(item.getCur_Periodicity());
        dto.setCur_DateStart(item.getCur_DateStart());
        dto.setCur_DateEnd(item.getCur_DateEnd());

        return currencyDao.save(dto);
    }

}
