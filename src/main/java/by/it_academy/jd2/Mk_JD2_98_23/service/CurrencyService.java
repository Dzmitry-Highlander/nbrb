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
        dto.setCurID(item.getCurID());
        dto.setCurCode(item.getCurCode());
        dto.setCurAbbreviation(item.getCurAbbreviation());
        dto.setCurName(item.getCurName());
        dto.setCurNameEng(item.getCurNameEng());
        dto.setCurQuotName(item.getCurQuotName());
        dto.setCurQuotNameBel(item.getCurQuotNameBel());
        dto.setCurQuotNameEng(item.getCurQuotNameEng());
        dto.setCurNameMulti(item.getCurNameMulti());
        dto.setCurNameBelMulti(item.getCurNameBelMulti());
        dto.setCurNameEngMulti(item.getCurNameEngMulti());
        dto.setCurScale(item.getCurScale());

        return currencyDao.save(dto);
    }

}
