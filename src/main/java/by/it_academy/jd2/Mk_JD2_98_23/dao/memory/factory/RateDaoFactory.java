package by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.api.ICurrencyDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.CurrencyMemoryDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.RateMemoryDao;

public class RateDaoFactory {

    private static volatile IRateDao instance;

    private RateDaoFactory() {
    }

    public static IRateDao getInstance() {
        if (instance == null)  {
            synchronized (RateDaoFactory.class) {
                if (instance == null) {
                    instance = new RateMemoryDao();
                }
            }

        }

        return instance;
    }
}