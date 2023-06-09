package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RatePeriodDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IBankApiService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IRateService;
import by.it_academy.jd2.Mk_JD2_98_23.service.exceptions.ServiceException;
import jakarta.servlet.ServletException;

import java.time.LocalDate;
import java.util.List;

public class RateService implements IRateService {
    private final IRateDao rateDao;
    private final IBankApiService bankApiService;

    public RateService(IRateDao rateDao, IBankApiService bankApiService) {
        this.rateDao = rateDao;
        this.bankApiService = bankApiService;
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
            if (year != null && !year.isEmpty() && yearValidate(year) && monthValidate(month)) {
                if (month.length() == 1) {
                    month = "0" + month;
                }
                date = LocalDate.parse(year + "-" + month + "-01");
            } else if (monthValidate(month)) {
                date = LocalDate.now().withMonth(Integer.parseInt(month)).withDayOfMonth(1);
            } else {
                throw new ServiceException("Дата не прошла валидацию, требуется диапазон с 01.12.2022 по 31.05.2023");
            }
            if (!dateValidate(date)) {
                throw new ServiceException("Дата не прошла валидацию, требуется диапазон с 01.12.2022 по 31.05.2023");
            }
            return rateDao.getAverageCurrency(date, currency);
        } catch (NumberFormatException e) {
            throw new ServiceException("Ошибка при формировании даты для среднего курса", e);
        }
    }


    @Override
    public void upload(RateCreateDTO item) {
        item.setCurID(item.getCurID());
        item.setDate(item.getDate().toLocalDate().atStartOfDay());
        item.setCurOfficialRate(item.getCurOfficialRate());

        rateDao.save(item);
    }

    @Override
    public boolean checkRateDataPeriod(RatePeriodDTO item) {
        return rateDao.checkRateDataPeriod(item);
    }

    @Override
    public boolean checkRateData(RateCreateDTO item) {
        return rateDao.checkRateData(item);
    }

    @Override
    public boolean dateValidate(String item) {
        boolean result = false;
        LocalDate after = LocalDate.of(2022, 12, 1);
        LocalDate before = LocalDate.of(2023, 5, 31);

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
        LocalDate after = LocalDate.of(2022, 12, 1);
        LocalDate before = LocalDate.of(2023, 5, 31);

        if ((date.isEqual(after) || !date.isBefore(after)) && !date.isAfter(before)) {
            result = true;
        }

        return result;
    }

    @Override
    public List<RateDTO> getPeriod(RatePeriodDTO item) {
        return rateDao.getPeriod(item);
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

    @Override
    public List<RateDTO> checkAndLoadDataFromApi(int cur, RatePeriodDTO item) {
        List<RateDTO> rateDTOS;
        if (dateValidate(item.getStartDate()) && dateValidate(item.getEndDate())) {

            if (!checkRateDataPeriod(item)) {

                List<RateCreateDTO> rateCreateDTOS = bankApiService.getRatesFromExternalAPI(cur,item);

                for (RateCreateDTO rateCreateDTO : rateCreateDTOS) {
                    if (checkRateData(rateCreateDTO)) {
                        upload(rateCreateDTO);
                    }
                }
            }

            rateDTOS = getPeriod(item);

        } else {
            try {
                throw new ServletException("Некорректная дата или код валюты! Введите дату в формате yyyy-mm-dd, " +
                        "с 2022-12-01 до 2023-05-31. Код валюты например USD.");
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
        return rateDTOS;
    }
}
