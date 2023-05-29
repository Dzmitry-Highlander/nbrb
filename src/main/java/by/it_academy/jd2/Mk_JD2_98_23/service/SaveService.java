package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.ISaveService;

import java.util.ArrayList;
import java.util.List;

public class SaveService implements ISaveService {
    @Override
    public List<RateDTO> save(RateCreateDTO item) {
        return new ArrayList<>();
    }
}
