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
        dto.setCur_ID(item.getCurID());
        dto.setCur_ParentID(item.getCurParentID());
        dto.setCur_Code(item.getCurCode());
        dto.setCur_Abbreviation(item.getCurAbbreviation());
        dto.setCur_Name(item.getCurName());
        dto.setCur_Name_Eng(item.getCurNameEng());
        dto.setCur_QuotName(item.getCurQuotName());
        dto.setCur_QuotName_Bel(item.getCurQuotNameBel());
        dto.setCur_QuotName_Eng(item.getCurQuotNameEng());
        dto.setCur_NameMulti(item.getCurNameMulti());
        dto.setCur_Name_BelMulti(item.getCurNameBelMulti());
        dto.setCur_Name_EngMulti(item.getCurNameEngMulti());
        dto.setCur_Scale(item.getCurScale());
        dto.setCur_Periodicity(item.getCurPeriodicity());
        dto.setCur_DateStart(item.getCurDateStart());
        dto.setCur_DateEnd(item.getCurDateEnd());

        return currencyDao.save(dto);
    }

}
