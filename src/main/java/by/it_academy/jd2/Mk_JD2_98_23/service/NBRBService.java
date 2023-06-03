package by.it_academy.jd2.Mk_JD2_98_23.service;


import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
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
    private static final String BASE_URL = "https://www.nbrb.by/api/exrates/";

    @Override
    public List<RateCreateDTO> getRatesFromExternalAPI(int cur, RatePeriodDTO item) {
        String url = BASE_URL + "dynamics/" + cur + "?startdate=" + item.getStartDate() + "&enddate=" + item.getEndDate();
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
}