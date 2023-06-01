package by.it_academy.jd2.Mk_JD2_98_23.dao.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;

public interface IRateDao extends ICRUDDao<RateCreateDTO> {
    void save(RateCreateDTO item);
}
