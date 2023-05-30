package by.it_academy.jd2.Mk_JD2_98_23.dao.memory;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.ICurrencyDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrencyMemoryDao implements ICurrencyDao {

    private final Map<Integer, CurrencyDTO> currencys = new ConcurrentHashMap<>();

    @Override
    public List<CurrencyDTO> get() {
        return new ArrayList<>(this.currencys.values());
    }

    @Override
    public CurrencyDTO get(int id) {
        return this.currencys.get(id);
    }

    @Override
    public CurrencyDTO save(CurrencyDTO item) {
        this.currencys.put(item.getCurID(),item);
        return item;
    }
}
