package by.it_academy.jd2.Mk_JD2_98_23.service.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory.RateShortDaoFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.RateShortService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IRateShortService;

public class RateShortServiceFactory {
    private static volatile IRateShortService instance;

    private RateShortServiceFactory() {
    }

    public static IRateShortService getInstance() {
        if (instance == null)  {
            synchronized (RateShortServiceFactory.class) {
                if (instance == null) {
                    instance =new RateShortService(RateShortDaoFactory.getInstance());
                }
            }

        }
        return instance;
    }
}
