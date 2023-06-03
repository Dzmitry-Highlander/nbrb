package by.it_academy.jd2.Mk_JD2_98_23.service.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RatePeriodDTO;

import java.util.List;

public interface IBankApiService {
    List<RateCreateDTO> getRatesFromExternalAPI(int cur, RatePeriodDTO item);
}
