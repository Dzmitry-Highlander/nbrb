package by.it_academy.jd2.Mk_JD2_98_23.controllers.listiners;


import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.DataInsertionErrorException;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.ICurrencyService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.CurrencyServiceFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@WebListener
public class AppServletContextListener implements ServletContextListener {

    private final ICurrencyService currencyService;
    private final ObjectMapper objectMapper;

    public AppServletContextListener() {
        this.currencyService = CurrencyServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        int count = this.currencyService.getCount();
        if (count == 0) {
            try {
                String url = "https://api.nbrb.by/exrates/currencies";
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");

                List<CurrencyCreateDTO> list = this.objectMapper.readValue(con.getInputStream(), new TypeReference<>() {
                });

                for (CurrencyCreateDTO dto : list) {
                    this.currencyService.uploadData(dto);
                }

            } catch (IOException e) {
                throw new DataInsertionErrorException("Ошибка загрузки валют из API", e);
            }
        }
    }
}