package by.it_academy.jd2.Mk_JD2_98_23.service.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory.RateDaoFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.SaveService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.ISaveService;

public class SaveServiceFactory {
    private static volatile ISaveService instance;

    private SaveServiceFactory() {
    }

    public static ISaveService getInstance(){
        if(instance == null){
            synchronized (SaveServiceFactory.class){
                if(instance == null){
                    instance = new SaveService(RateDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
