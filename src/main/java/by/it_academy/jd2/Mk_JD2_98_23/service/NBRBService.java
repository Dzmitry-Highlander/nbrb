package by.it_academy.jd2.Mk_JD2_98_23.service;


import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyRateTodayDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RatePeriodDTO;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IBankApiService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class NBRBService implements IBankApiService {
    private static final String BASE_URL = "https://api.nbrb.by/exrates/";

    @Override
    public List<RateCreateDTO> getRatesFromExternalAPI(int cur, RatePeriodDTO item) {
        String url = BASE_URL + "rates/dynamics/" + cur + "?startdate=" + item.getStartDate() + "&enddate=" + item.getEndDate();
        List<RateCreateDTO> rateCreateDTOS;
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

    @Override
    public int getCurIdFromApi(String curAbb) {
        String url = BASE_URL + "rates/" + curAbb + "?parammode=2";
        URL obj = null;
        CurrencyRateTodayDTO currencyRateTodayDTO = null;
        try {
            obj = new URL(url);
            ObjectMapper mapper = ObjectMapperFactory.getInstance();
            currencyRateTodayDTO = mapper.readValue(obj, CurrencyRateTodayDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Этот тип валюты не представлен в API", e);
        }
        return currencyRateTodayDTO.getCurID();
    }

    @Override
    public CurrencyCreateDTO getSelectedCurrencyFromApi(int curId) {
        String url = BASE_URL+ "currencies/" + curId;
        URL obj = null;
        CurrencyCreateDTO currencyCreateDTO = null;
        try {
            obj = new URL(url);
            ObjectMapper mapper = ObjectMapperFactory.getInstance();
            currencyCreateDTO = mapper.readValue(obj, CurrencyCreateDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при получении новой валюты из API", e);
        }
        return currencyCreateDTO;
    }
}