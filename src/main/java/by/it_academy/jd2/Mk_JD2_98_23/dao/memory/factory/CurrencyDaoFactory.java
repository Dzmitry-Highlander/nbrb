package by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.api.ICurrencyDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.CurrencyJDBCDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.CurrencyMemoryDao;

public class CurrencyDaoFactory {

    private static volatile ICurrencyDao instance;

    private CurrencyDaoFactory() {
    }

    public static ICurrencyDao getInstance() {
        if (instance == null)  {
            synchronized (CurrencyDaoFactory.class) {
                if (instance == null) {
                    instance = new CurrencyJDBCDao();
                }
            }

        }

        return instance;
    }
}
