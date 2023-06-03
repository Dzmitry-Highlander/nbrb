package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RatePeriodDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IRateService;
import by.it_academy.jd2.Mk_JD2_98_23.service.exceptions.ServiceException;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
            LocalDate date = null;
            if (year != null && !year.isEmpty() && yearValidate(year) && monthValidate(month)) {
                if (month.length() == 1) { // проверяем длину строки
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

    public List<RateCreateDTO> getRatesFromExternalAPI(int cur, RatePeriodDTO item) {
        String url = "https://api.nbrb.by/exrates/rates/dynamics/" + cur + "?startdate=" + item.getStartDate() + "&enddate=" + item.getEndDate();
        List<RateCreateDTO> rateCreateDTOS = null;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            ObjectMapper objectMapper = ObjectMapperFactory.getInstance();
            try (InputStream inputStream = con.getInputStream()) {
                rateCreateDTOS = objectMapper.readValue(inputStream,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, RateCreateDTO.class));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rateCreateDTOS;
    }
}
