package by.it_academy.jd2.Mk_JD2_98_23.service.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory.RateDaoFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.RateService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IRateService;

public class RateServiceFactory {
    private static volatile IRateService instance;

    private RateServiceFactory() {
    }

    public static IRateService getInstance() {
        if (instance == null)  {
            synchronized (RateServiceFactory.class) {
                if (instance == null) {
                    instance =new RateService(RateDaoFactory.getInstance(), BankApiServiceFactory.getInstance());
                }
            }

        }
        return instance;
    }
}
