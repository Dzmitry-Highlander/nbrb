package by.it_academy.jd2.Mk_JD2_98_23.service.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;

public interface IRateService  extends ICRUDService<RateCreateDTO, RateCreateDTO>{
    void upload(RateCreateDTO item);

    boolean validate(RateCreateDTO item);
}
