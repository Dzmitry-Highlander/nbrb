package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IRateService;

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
    public RateCreateDTO get(int id) {
        return rateDao.get(id);
    }

    @Override
    public RateCreateDTO save(RateCreateDTO item) {
        RateCreateDTO dto = new RateCreateDTO();
        dto.setCurID(item.getCurID());
        dto.setDate(item.getDate());
        dto.setCurAbbreviation(item.getCurAbbreviation());
        dto.setCurScale(item.getCurScale());
        dto.setCurName(item.getCurName());
        dto.setCurOfficialRate(item.getCurOfficialRate());

        return rateDao.save(dto);
    }
}
