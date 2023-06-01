package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IRateService;
import by.it_academy.jd2.Mk_JD2_98_23.service.exceptions.ServiceException;

import java.time.LocalDate;
import java.util.List;

public class RateService implements IRateService {
    private final IRateDao rateDao;

    public RateService(IRateDao rateDao) {
        this.rateDao = rateDao;
    }

    @Override
    public List<RateCreateDTO> get() {
        return rateDao.get();
    }

    @Override
    public RateCreateDTO get(int cur_ID) {
        return rateDao.get(cur_ID);
    }

    @Override
    public List<RateDTO> get(String curAbbreviation) {
        return rateDao.get(curAbbreviation);
    }

    @Override
    public double getAverageCurrency(String currency, String year, String month) {

        try {
            LocalDate date;
            if (year == null || year.isEmpty() && monthValidate(month)) {
                date = LocalDate.now().withMonth(Integer.parseInt(month)).withDayOfMonth(1);
                if (dateValidate(date)) { return rateDao.getAverageCurrency(date, currency);}

            } else if (monthValidate(month) || yearValidate(year)) {
                date = LocalDate.parse(year + "-" + month + "-01");
                if (dateValidate(date)) { return rateDao.getAverageCurrency(date, currency);}
            }
        } catch (NumberFormatException e) {
            throw new ServiceException("Ошибка при формировании даты для среднего курса", e);
        }

        return 0;
    }


    @Override
    public void upload(RateCreateDTO item) {
        item.setCurID(item.getCurID());
        item.setDate(item.getDate().toLocalDate().atStartOfDay());
        item.setCurOfficialRate(item.getCurOfficialRate());

        rateDao.save(item);
    }

    @Override
    public boolean checkRateDataPeriod(String curAbbreviation, LocalDate dateStart, LocalDate dateEnd) {
        return rateDao.checkRateDataPeriod(curAbbreviation, dateStart, dateEnd);
    }

    @Override
    public boolean checkRateData(RateCreateDTO item) {
        return rateDao.checkRateData(item);
    }

    @Override
    public boolean dateValidate(String item) {
        boolean result = false;
        LocalDate after = LocalDate.parse("2022-12-01");
        LocalDate before = LocalDate.parse("2023-05-31");

        if (item.matches("\\d{4}-\\d{2}-\\d{2}")) {
            LocalDate dateToCheck = LocalDate.parse(item);

            if (dateToCheck.isBefore(before) && dateToCheck.isAfter(after)) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public boolean dateValidate(LocalDate date) {
        boolean result = false;
        LocalDate after = LocalDate.parse("2022-12-01");
        LocalDate before = LocalDate.parse("2023-05-31");

        if (date.isBefore(before) && date.isAfter(after)) {
            result = true;
        }

        return result;
    }

    @Override
    public boolean currencyValidate(String item) {
        return true;
    }

    public boolean monthValidate(String month) {
        try {
            int monthInt = Integer.parseInt(month);
            return monthInt >= 1 && monthInt <= 12;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean yearValidate(String year) {
        try {
            int yearInt = Integer.parseInt(year);
            return year.length() == 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
