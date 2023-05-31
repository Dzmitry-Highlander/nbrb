package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateShortDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateShortDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IRateShortService;

import java.util.List;

public class RateShortService implements IRateShortService {

    private final IRateShortDao rateShortDao;

    public RateShortService(IRateShortDao rateShortDao) {
        this.rateShortDao = rateShortDao;
    }

    @Override
    public List get() {
        return  this.rateShortDao.get();
    }

    @Override
    public RateShortDTO get(int id) {
        return  this.rateShortDao.get(id);
    }
}
