package by.it_academy.jd2.Mk_JD2_98_23.service.factory;

import by.it_academy.jd2.Mk_JD2_98_23.service.NBRBService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IBankApiService;

public class BankApiServiceFactory {
    private static volatile IBankApiService instance;

    private BankApiServiceFactory() {}

    public static IBankApiService getInstance() {
        if (instance == null) {
            synchronized (BankApiServiceFactory.class) {
                if (instance == null) {
                    instance = new NBRBService();
                }
            }
        }
        return instance;
    }
}
