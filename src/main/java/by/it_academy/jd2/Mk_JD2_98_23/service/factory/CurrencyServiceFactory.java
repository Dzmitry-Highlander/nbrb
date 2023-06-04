package by.it_academy.jd2.Mk_JD2_98_23.service.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory.CurrencyDaoFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.CurrencyService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.ICurrencyService;

public class CurrencyServiceFactory {
    private static volatile ICurrencyService instance;

    private CurrencyServiceFactory() {
    }

    public static ICurrencyService getInstance() {
        if (instance == null)  {
            synchronized (CurrencyServiceFactory.class) {
                if (instance == null) {
                    instance =new CurrencyService(CurrencyDaoFactory.getInstance(), BankApiServiceFactory.getInstance());
                }
            }

        }
        return instance;
    }
}
