package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
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
    public RateDTO get(int id) {
        return rateDao.get(id);
    }

    @Override
    public RateDTO save(RateCreateDTO item) {
        RateDTO dto = new RateDTO();
        dto.setCur_ID(item.getCur_ID());
        dto.setDate(item.getDate());
        dto.setCur_Abbreviation(item.getCur_Abbreviation());
        dto.setCur_Scale(item.getCur_Scale());
        dto.setCur_Name(item.getCur_Name());
        dto.setCur_OfficialRate(item.getCur_OfficialRate());

        return rateDao.save(dto);
    }
}
