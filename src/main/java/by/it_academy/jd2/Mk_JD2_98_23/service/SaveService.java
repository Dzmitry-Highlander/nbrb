package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.ISaveService;

import java.util.List;

public class SaveService implements ISaveService {
    private final IRateDao rateDao;

    public SaveService(IRateDao rateDao) {
        this.rateDao = rateDao;
    }

    @Override
    public List<RateDTO> get() {
        return rateDao.get();
    }

    @Override
    public RateDTO get(int id) {
        return rateDao.get(id);
    }

    @Override
    public RateDTO save(RateCreateDTO item) {
        RateDTO dto = new RateDTO();
        int maxCurrentId = this.get()
                .stream()
                .mapToInt(RateDTO::getId)
                .max()
                .orElseThrow();

        dto.setCur_ID(item.getCur_ID());

        return rateDao.save(dto);
    }
}
