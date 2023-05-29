package by.it_academy.jd2.Mk_JD2_98_23.dao.memory;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateMemoryDao implements IRateDao {
    private final Map<Integer, RateDTO> rateDTOMap = new ConcurrentHashMap<>();

    public RateMemoryDao() {
    }

    @Override
    public List<RateDTO> get() {
        return new ArrayList<>(this.rateDTOMap.values());
    }

    @Override
    public RateDTO get(int id) {
        return this.rateDTOMap.get(id);
    }

    @Override
    public RateDTO save(RateDTO item) {
        return this.rateDTOMap.put(item.getId(), item);
    }
}
