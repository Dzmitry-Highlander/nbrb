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
    public List<CurrencyDTO> get() {
        return currencyDao.get();
    }

    @Override
    public CurrencyDTO get(int id) {
        return currencyDao.get(id);
    }

    @Override
    public CurrencyDTO save(CurrencyCreateDTO item) {
        return null;
    }

    @Override
    public void uploadData(CurrencyCreateDTO item) {
         currencyDao.uploadData(item);
    }

    @Override
    public int getCount() {
        return currencyDao.getCount();
    }
}
