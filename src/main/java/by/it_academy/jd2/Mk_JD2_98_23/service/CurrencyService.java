package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.ICurrencyDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IBankApiService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.ICurrencyService;

import java.util.List;

public class CurrencyService implements ICurrencyService {
    private final ICurrencyDao currencyDao;
    private final IBankApiService bankApiService;

    public CurrencyService(ICurrencyDao currencyDao, IBankApiService bankApiService) {
        this.currencyDao = currencyDao;
        this.bankApiService = bankApiService;
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
    public void uploadData(CurrencyCreateDTO item) {
        currencyDao.uploadData(item);
    }

    @Override
    public int getCount() {
        return currencyDao.getCount();
    }

    @Override
    public int getCurID(String curAbbreviation) {
        return currencyDao.getCurID(curAbbreviation);
    }

    @Override
    public boolean currencyValidate(String curAbb) {
        if (!currencyDao.currencyValidate(curAbb)) {
            int curId = bankApiService.getCurIdFromApi(curAbb);
                uploadData(bankApiService.getSelectedCurrencyFromApi(curId));
                return true;
        }
        return currencyDao.currencyValidate(curAbb);
    }
}
