package by.it_academy.jd2.Mk_JD2_98_23.service.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;

import java.util.List;

public interface ICRUDService<T> {
        List<T> get();

        T get(int id);

        T save(RateDTO item);
}
