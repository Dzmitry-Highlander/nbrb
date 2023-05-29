package by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.api.ICurrencyDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.CurrencyMemoryDao;

public class RateDaoFactory {

    private static volatile ICurrencyDao instance;

    private RateDaoFactory() {
    }

    public static ICurrencyDao getInstance() {
        if (instance == null)  {
            synchronized (RateDaoFactory.class) {
                if (instance == null) {
                    instance = new CurrencyMemoryDao();
                }
            }

        }

        return instance;
    }
}
