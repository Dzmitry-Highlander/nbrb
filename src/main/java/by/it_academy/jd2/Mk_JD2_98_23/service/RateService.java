package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IRateService;

import java.time.LocalDate;
import java.util.List;

public class RateService implements IRateService {

    private final IRateDao rateDao;

    public RateService(IRateDao rateDao) {
        this.rateDao = rateDao;
    }


    @Override
    public List get() {
        return rateDao.get();
    }

    @Override
    public RateDTO get(int id) {
        return rateDao.get(id);
    }

    @Override
    public void save(RateCreateDTO item) {
     rateDao.save(item);
    }

    @Override
    public boolean checkPeriod(LocalDate from, LocalDate to) {
        return false;
    }

}
