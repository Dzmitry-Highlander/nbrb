package by.it_academy.jd2.Mk_JD2_98_23.dao.memory;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateShortDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateShortDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateShortMemoryDao implements IRateShortDao {

    private final Map<Integer, RateShortDTO> rateShortMap = new ConcurrentHashMap<>();

    public RateShortMemoryDao() {
    }

    @Override
    public List<RateShortDTO> get() {
        return new ArrayList<>(this.rateShortMap.values());
    }

    @Override
    public RateShortDTO get(int id) {
        return this.rateShortMap.get(id);
    }

    @Override
    public RateShortDTO save(RateShortDTO item) {
        return this.rateShortMap.put(item.getId(), item);
    }
}
